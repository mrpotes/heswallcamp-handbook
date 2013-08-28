package uk.org.heswallcamp.handbook.data.resources;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.security.KeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.bouncycastle.openssl.PEMParser;

import uk.org.heswallcamp.handbook.data.dao.BoysDAO;
import uk.org.heswallcamp.handbook.data.model.Boy;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

@Singleton
@Path("/boys")
@Produces(MediaType.APPLICATION_JSON)
public class BoysResource {

	@Inject
	BoysDAO dao;

	@GET
	public Boy getBoy(@QueryParam("name") String name) {
		return dao.getBoy(name);
	}

	@GET
	@Path("/{year}")
	public List<Boy> getBoys(@PathParam("year") Integer year) {
		return dao.getBoys(year);
	}

	@POST
	public ObjectNode getBoysSecure(@FormParam("key") String key,
			@FormParam("year") Integer year) throws IOException, NoSuchAlgorithmException, NoSuchPaddingException, KeyException, IllegalBlockSizeException, BadPaddingException {
		PEMParser reader = new PEMParser(new StringReader(key));
		try {
			System.out.println(reader.readObject().getClass().getName());
			StringWriter w = new StringWriter();
			ObjectMapper om = new ObjectMapper();
			KeyGenerator kgen = KeyGenerator.getInstance("AES");
			kgen.init(256);
			SecretKey skey = kgen.generateKey();
			om.writeValue(w, getBoys(year));
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding");
			cipher.init(Cipher.ENCRYPT_MODE, skey);
			byte[] output = cipher.doFinal(w.toString().getBytes("UTF-8"));
			return om.createObjectNode().put("key", skey.getEncoded()).put("payload", output);
		} finally {
			reader.close();
		}
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public void setBoy(Boy boy) {
		dao.save(boy);
	}

}
