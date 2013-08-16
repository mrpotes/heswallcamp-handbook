window.Handbook = window.Handbook || {views: {}};
Handbook.views.home = function(params) {

  var viewModel = {
    programme: [
      {time: "08:30", activity: "Breakfast", details: "Joe Bloggs<br/>Jane Doe", icon: "food"},
      {time: "10:00", activity: "Coach", details: "to Chester Zoo"},
      {time: "10:30", activity: "Chester Zoo"},
      {time: "12:30", activity: "Lunch", details: "on Oakfield Lawn", icon: "food"},
      {time: "16:30", activity: "Coach"},
      {time: "19:00", activity: "BBQ and Disco"},
      {time: "21:30", activity: "Bedtime"},
      {time: "23:00", activity: "Night Shift",iconSrc : "img/moon.png", details: "Fred, Gary"},
      {time: "21:30", activity: "Night Shift",iconSrc : "img/moon.png", details: "Fred, Gary"},
      {time: "21:30", activity: "Night Shift",iconSrc : "img/moon.png", details: "Fred, Gary"},
      {time: "21:30", activity: "Night Shift",iconSrc : "img/moon.png", details: "Fred, Gary"},
      
    ]
  };

  return viewModel;
};