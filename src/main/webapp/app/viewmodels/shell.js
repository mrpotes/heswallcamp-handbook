define(['plugins/router', 'durandal/app'], function (router, app) {
    return {
        router: router,
        search: function() {
            //It's really easy to show a message box.
            //You can add custom options too. Also, it returns a promise for the user's response.
            app.showMessage('Search not yet implemented...');
        },
        activate: function () {
            router.map([
                        { route: '', title:'Home', moduleId: 'viewmodels/welcome', nav: true },
                        { route: 'boys', title:'Boys', moduleId: 'viewmodels/boys', nav: true },
                        { route: 'programme', title:'Programme', moduleId: 'viewmodels/programme', nav: true },
                        { route: 'helpers', title:'Helpers', moduleId: 'viewmodels/helpers', nav: true },
                        { route: 'dorms', title:'Dorm Allocation', moduleId: 'viewmodels/dorms', nav: true },
                        { route: 'nights', title:'Night Shifts', moduleId: 'viewmodels/nights', nav: true },
                        { route: 'kitchen', title:'Kitchen Rota', moduleId: 'viewmodels/kitchen', nav: true },
                        { route: 'admin', title:'Admin', moduleId: 'viewmodels/admin', nav: true }
            ]).buildNavigationModel();
            
            return router.activate();
        }
    };
});