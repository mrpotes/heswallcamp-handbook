window.Handbook = window.Handbook || {views: {}};
Handbook.views.pin = function() {
  var viewModel = {
      pin : ko.observable(),
      createLogin : function() {
        var crypt = new JSEncrypt();
        localStorage.setItem("privateKey", CryptoJS.TripleDES.encrypt(crypt.getPrivateKey(), this.pin()));
        localStorage.setItem("publicKey", crypt.getPublicKey());
        Handbook.app.navigate("loading", {root: true});
      }
    };
  return viewModel; 
};
