window.Handbook = window.Handbook || {
  views : {}
};
Handbook.views.about = function() {
  var viewModel = {
      logout: function() {
        localStorage.clear();
        Handbook.app.navigate("login", {root : true});
      }
  };
  return viewModel;
}