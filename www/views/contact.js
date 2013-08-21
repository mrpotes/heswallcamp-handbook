window.Handbook = window.Handbook || {
  views : {}
};
Handbook.views.contact = function(params) {
  var viewModel = {
    contact: ko.observable({name: params.id}),
    viewShown: function() {
      database.getHelper(params.id, function(helper) {
        viewModel.contact(helper);
      });
    }
  };
  return viewModel;
}
