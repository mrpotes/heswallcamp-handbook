window.Handbook = window.Handbook || {
  views : {}
};
Handbook.views.login = function() {
  var viewModel = {
    pin : ko.observable(),
    oauth : ko.observable(localStorage.getItem("oauth_token")),
    error : ko.observable(),
    isRippleEmulator: window.tinyHippos != undefined,
    oauthCode : ko.observable(),
    viewShown: function() {
      Handbook.pin = null;
      this.oauth(localStorage.getItem("oauth_token"));
      this.oauthCode(null);
      this.error(null);
      this.pin(null);
    },
    signInWithGoogle : function() {
      // Build the OAuth consent page URL
      var authUrl = 'https://accounts.google.com/o/oauth2/auth?' + $.param({
        client_id : Handbook.oauth.clientId,
        redirect_uri : "http://localhost",
        response_type : "code",
        scope : "https://www.googleapis.com/auth/drive.readonly https://sites.google.com/feeds/ https://www.googleapis.com/auth/userinfo.email"
      });

      // Open the OAuth consent page in the InAppBrowser
      var authWindow = window.open(authUrl, '_blank', 'location=no,toolbar=no');

      authWindow.addEventListener('loaderror', function(e) {
        console.log("Loading URL: "+e.url);
        var url = e.url;
        var code = /\?code=(.+)$/.exec(url);
        var error = /\?error=(.+)$/.exec(url);

        if (code || error) {
          // Always close the browser when match is found
          authWindow.close();
        }

        if (code) {
          viewModel.oauthCode(code[1]);
          viewModel.swapAuthCodeForAccessToken();
        } else if (error) {
          viewModel.error(error[1]);
        }
      });
    },
    swapAuthCodeForAccessToken: function() {
      console.log("Got oauth code: "+this.oauthCode());
      // Exchange the authorization code for an access token
      $.post('https://accounts.google.com/o/oauth2/token', {
        code : this.oauthCode(),
        client_id : Handbook.oauth.clientId,
        client_secret : Handbook.oauth.clientSecret,
        redirect_uri : "http://localhost",
        grant_type : 'authorization_code'
      }).done(function(data) {
        console.log("Got authorization code");
        $.get('https://sites.google.com/feeds/content/hdchf.org.uk/members?access_token='+data.access_token).done(function() {
          console.log("Successfully connected to the members google site. Looks Good.")
          localStorage.setItem("oauth_token", data.access_token);
          localStorage.setItem("oauth_expires", new Date().getTime() + (data.expires_in * 1000));
          localStorage.setItem("oauth_refresh", data.refresh_token);
          Handbook.app.navigate("pin", {root : true});
        }).fail(function() {
          console.log("Failed to get google sites content");
          viewModel.error("Sorry, you do not have permissions to access the site.");
        });
      }).fail(function(response, status, error) {
        console.log("Failed to get token: response["+response.responseText+"] status["+status+"] error["+error+"]");
        viewModel.error(response.responseText);
      });
    },
    login : function() {
      Handbook.pin = viewModel.pin();
      Handbook.app.navigate("home", {
        root : true
      });
    }
  };
  return viewModel;
};
