window.Handbook = window.Handbook || {
  views : {}
};
Handbook.views.login = function() {
  var viewModel = {
    pin : ko.observable(),
    oauth : ko.observable(localStorage.getItem("oauth_token")),
    error : ko.observable(),
    
    signInWithGoogle : function() {
      // Build the OAuth consent page URL
      var authUrl = 'https://accounts.google.com/o/oauth2/auth?' + $.param({
        client_id : Handbook.oauth.clientId,
        redirect_uri : "http://localhost",
        response_type : "code",
        scope : "https://www.googleapis.com/auth/drive.readonly https://sites.google.com/feeds/"
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
          console.log("Got oauth code: "+code[1]);
          // Exchange the authorization code for an access token
          $.post('https://accounts.google.com/o/oauth2/token', {
            code : code[1],
            client_id : Handbook.oauth.clientId,
            client_secret : Handbook.oauth.clientSecret,
            redirect_uri : "http://localhost",
            grant_type : 'authorization_code'
          }).done(function(data) {
            console.log("Got authorization code");
            $.get('https://sites.google.com/feeds/content/hdchf.org.uk/members?access_token='+data.access_token).done(function() {
              localStorage.setItem("oauth_token", data.access_token);
              Handbook.app.navigate("pin", {root : true});
            }).fail(function() {
              console.log("Failed to get google sites content");
              viewModel.error("Sorry, you do not have permissions to access the site.");
            });
          }).fail(function(response, status, error) {
            console.log("Failed to get token: response["+response.responseText+"] status["+status+"] error["+error+"]");
            viewModel.error(response.responseText);
          });
        } else if (error) {
          viewModel.error(error[1]);
        }
      });
    },
    login : function() {
      Handbook.app.navigate("home", {
        root : true
      });
    }
  };
  return viewModel;
};