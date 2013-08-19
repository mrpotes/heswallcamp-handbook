window.Handbook = window.Handbook || {
  views : {}
};
Handbook.views.handbook = function() {
  var viewModel = {
    handbook: localStorage.getItem("handbook")
  };
  return viewModel;
}