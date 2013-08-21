window.Handbook = window.Handbook || {
  views : {}
};
Handbook.views.contacts = function() {
  var viewModel = {
    contacts: ko.observableArray([
      {
        key: "Helpers",
        items: []
      }
    ]),
    viewShown: function() {
      database.getHelpers(function(helpers) {
        viewModel.contacts.shift();
        viewModel.contacts.unshift({key:"Helpers",items:helpers});
      });
    },
    clickHelper: function(data) {
      Handbook.app.navigate("contact/"+data.itemData.name);
    }
  };
  return viewModel;
}
