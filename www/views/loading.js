window.Handbook = window.Handbook || {
  views : {}
};
Handbook.views.loading = function() {
  var viewModel = {
    toLoad : [ {
      label : "Handbook", state: ko.observable("loading"), next: function() {viewModel.loadBoys();}
    }, {
      label : "Boys", state: ko.observable(""), next: function() {viewModel.loadHelpers();}
    }, {
      label : "Helpers", state: ko.observable(""), next: function() {viewModel.loadKitchenRota();}
    }, {
      label : "Kitchen Rota", state: ko.observable(""), next: function() {viewModel.loadNightRota();}
    }, {
      label : "Night Rota", state: ko.observable(""), next: function() {viewModel.loadDorms();}
    }, {
      label : "Dorm Allocation", state: ko.observable(""), next: function() {Handbook.app.navigate("home", {root : true});}
    } ],
    loading: 0,
    viewShown : function() {
      viewModel.toLoad[0].state("loading");
      for (var i = 1, current; current = this.toLoad[i]; i++) viewModel.toLoad[i].state(""); 
      this.loading = 0;
      console.log("View shown, getting content...");
      
      Handbook.oauth.get("https://www.googleapis.com/drive/v2/files/1xijdozPwBtAQHyHAzfNsRbBe1mDs9G8P5PiLFUlNQPU").done(function(data) {
        console.log("Got handbook document details");
        Handbook.oauth.get(data.exportLinks["text/html"]).done(function(data) {
          var split = data.indexOf("The information in the book");
          var header = data.substring(0, split);
          localStorage.setItem("handbook", data.substring(header.lastIndexOf("<p"), data.lastIndexOf("</body>")));
          viewModel.loadNext();
        }).fail(viewModel.loadFailed);
      }).fail(viewModel.loadFailed);
    },
    loadBoys : function() {
      Handbook.oauth.get("boys").done(function(data)) {
        var crypt = new JSEncrypt();
        crypt.setPrivateKey(CryptoJS.TripleDES.encrypt(localStorage.getItem("privateKey"), Handbook.pin));
        database.setBoys($.parseJSON(crypt.decrypt(data)), viewModel.loadNext, viewModel.loadFailed);
      }).fail(viewModel.loadFailed);
    },
    loadHelpers : function() {
      Handbook.oauth.get("https://sites.google.com/feeds/content/hdchf.org.uk/members?path=/annual-camp/helpers-directory").done(function(data) {
        var listUrl = data.documentElement.getElementsByTagName("entry")[0].getElementsByTagName("feedLink")[0].attributes['href'].value;
        Handbook.oauth.get(listUrl).done(function(data) {
          var helpers = [];
          var entries = data.documentElement.getElementsByTagName("entry");
          for (var i=0, entry; entry = entries[i]; i++) {
            var helper = {};
            var fields = entry.getElementsByTagName("field");
            for (var j=0, field; field = fields[j]; j++) {
              if (field.attributes["name"].value !== "Age") {
                var fieldValue = field.textContent;
                if (fieldValue.indexOf("<a") === 0) fieldValue = fieldValue.substring(fieldValue.indexOf(">")+1, fieldValue.lastIndexOf("<"));
                console.log("'"+field.attributes["name"].value+"' - "+fieldValue);
                helper[field.attributes["name"].value] = fieldValue;
              } 
            }
            helpers.push(helper);
          }
          database.setHelpers(helpers, viewModel.loadNext, viewModel.loadFailed);
        }).fail(viewModel.loadFailed)
      }).fail(viewModel.loadFailed);
    },
    loadKitchenRota : function() {
      viewModel.loadNext();
    },
    loadNightRota : function() {
      viewModel.loadNext();
    },
    loadDorms : function() {
      viewModel.loadNext();
    },
    loadFailed: function(jqXHR, textStatus, errorThrown) {
      console.log("Error: "+errorThrown);
      console.log("Error: "+jqXHR.status+" "+jqXHR.statusText+" - "+textStatus);
      console.log("Error: "+jqXHR.responseText);
      viewModel.loadNext(true);
    },
    loadNext: function(failed) {
      if (failed) viewModel.toLoad[viewModel.loading].state("failed");
      else viewModel.toLoad[viewModel.loading].state("loaded");
      var next = viewModel.toLoad[viewModel.loading].next;
      viewModel.loading += 1;
      if (viewModel.toLoad[viewModel.loading]) viewModel.toLoad[viewModel.loading].state("loading");
      next();
    }
  };
  return viewModel;
};
