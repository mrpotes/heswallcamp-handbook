/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
"use strict";

window.Handbook = window.Handbook || {views: {}};

var app = {
  // Application Constructor
  initialize : function() {
    this.bindEvents();
  },
  // Bind Event Listeners
  //
  // Bind any events that are required on startup. Common events are:
  // 'load', 'deviceready', 'offline', and 'online'.
  bindEvents : function() {
    document.addEventListener('deviceready', this.onDeviceReady, false);
  },
  // deviceready Event Handler
  //
  // The scope of 'this' is the event. In order to call the 'receivedEvent'
  // function, we must explicity call 'app.receivedEvent(...);'
  onDeviceReady : function() {
    app.receivedEvent('deviceready');
    database.initialise();
    var APP_SETTINGS = {
      namespace : Handbook.views,
      navigateToRootViewMode : "resetHistory",
      defaultLayout : "slideout",
      navigation : [ {
        title : "Home",
        action : "#home",
        icon : "home"
      }, {
        title : "Search",
        action : "#search",
        icon : "find"
      }, {
        title : "Handbook",
        action : "#handbook",
        icon : "doc"
      }, {
        title : "Contacts",
        action : "#contacts",
        icon : "card"
      }, {
        title : "Programme",
        action : "#programme",
        icon : "event"
      }, {
        title : "Kitchen Rota",
        action : "#kitchen",
        icon : "food"
      }, {
        title : "Night Shift Rota",
        action : "#nights",
        iconSrc : "img/moon.png"
      }, {
        title : "Dorm Allocation",
        action : "#dorms",
        icon : "map"
      }, {
        title : "Boys",
        action : "#boys",
        icon : "user"
      }, {
        title : "About",
        action : "#about",
        icon : "info"
      } ]
    };
    Handbook.app = new DevExpress.framework.html.HtmlApplication(APP_SETTINGS);
    if (window.tinyHippos != undefined) {
      Handbook.app.router.register(":view", {
        view : "loading"
      });
    }
    else {
      Handbook.app.router.register(":view", {
        view : "login"
      });
    }
    Handbook.app.navigate();
  },
  // Update DOM on a Received Event
  receivedEvent : function(id) {
    console.log('Received Event: ' + id);
  }
};
