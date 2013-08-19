window.Handbook = window.Handbook || {
  views : {}
};
Handbook.views.loading = function() {
  var viewModel = {
    toLoad : [ {
      label : "Handbook", state: ko.observable("loading")
    }, {
      label : "Boys", state: ko.observable("")
    }, {
      label : "Kitchen Rota", state: ko.observable("")
    }, {
      label : "Night Rota", state: ko.observable("")
    }, {
      label : "Dorm Allocation", state: ko.observable("")
    } ],
    viewShown : function() {
      viewModel.toLoad[0].state("loading");
      viewModel.toLoad[1].state("");
      viewModel.toLoad[2].state("");
      viewModel.toLoad[3].state("");
      viewModel.toLoad[4].state("");
      
      console.log("View shown, getting content...");
      var url = "https://www.googleapis.com/drive/v2/files/1xijdozPwBtAQHyHAzfNsRbBe1mDs9G8P5PiLFUlNQPU?access_token=" + localStorage.getItem("oauth_token");
      $.get(url).done(function(data) {
          console.log("Got handbook document details");
          var url = data.exportLinks["text/html"] + "&access_token=" + localStorage.getItem("oauth_token");
          console.log(url);
          $.get(url).done(function(data) {
            var split = data.indexOf("The information in the book");
            var header = data.substring(0, split);
            localStorage.setItem("handbook", data.substring(header.lastIndexOf("<p"), data.lastIndexOf("</body>")));
            viewModel.toLoad[0].state("loaded");
            viewModel.loadBoys();
          });
        }
      ).fail(function(e) {
        console.log("Error: "+e.responseText);
        viewModel.toLoad[0].state("failed");
        viewModel.loadBoys();
      });
    },
    loadBoys : function() {
      viewModel.toLoad[1].state("loading");
      viewModel.toLoad[1].state("loaded");
      this.loadKitchenRota();
    },
    loadKitchenRota : function() {
      viewModel.toLoad[2].state("loading");
      viewModel.toLoad[2].state("loaded");
      this.loadNightRota();
    },
    loadNightRota : function() {
      viewModel.toLoad[3].state("loading");
      viewModel.toLoad[3].state("loaded");
      this.loadDorms();
    },
    loadDorms : function() {
      viewModel.toLoad[4].state("loading");
      viewModel.toLoad[4].state("loaded");
      Handbook.app.navigate("home", {
        root : true
      });
    }
  };
  return viewModel;
};
