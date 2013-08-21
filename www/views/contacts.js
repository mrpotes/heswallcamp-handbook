window.Handbook = window.Handbook || {
  views : {}
};
Handbook.views.contacts = function() {
  var viewModel = {
    contacts: [
      {
        key: "Helpers",
        items: ko.observableArray([])
      }
    ],
    viewShown: function() {
      database.getHelpers(function(helpers) {
        for (var i=0, helper; helper = helpers[i]; i++) {
          console.log("Loaded "+helper.name);
          viewModel.contacts[0].items.push(helper);
        }
      });
    },
    helperPopupShown: ko.observable(false),
    popupHelper: ko.observable({name: '', dob: '', mobile: '', phone: '', address: '', notes: '', email: ''}),
    clickHelper: function(comp, el, itemData) {
      popupHelper(itemData);
      helperPopupShown(true);
    }
  };
  return viewModel;
}
