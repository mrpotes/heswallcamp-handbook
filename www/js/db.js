var database = {
  db : null,
    
  initialise: function(callback) {
    db = window.openDatabase("Database", "1.0", "HDCHF Data", 2000000);
    db.transaction(function(tx) {
      tx.executeSql("CREATE TABLE IF NOT EXISTS CONFIG (key,value)");
      tx.executeSql("CREATE TABLE IF NOT EXISTS BOYS (name,data)");
      tx.executeSql("CREATE TABLE IF NOT EXISTS HELPERS (name, dateofbirth, address, phonenumber, mobilenumber, email, notes)");
    }, database.errorHandler, function() {
      database.getKeys(callback);
    });
  },

  getKeys : function(callback) {
    db.transaction(function(tx) {
      var result = tx.executeSql("SELECT * FROM CONFIG WHERE key IN ('publicKey','privateKey')", [], function(tx, results) {
        if (results.length == 2) {
          for (var i = 0, item; item = results.item[i]; i++) {
            window.localStorage.setItem(item.key, item.value);
          }
        }
        if (callback) callback();
      }, database.errorHandler);
    }, database.errorHandler, database.successHandler);
  },
  
  setBoys: function (boys, success, failure) {
    db.transaction(function(tx) {
      tx.executeSql("DROP TABLE BOYS");
      tx.executeSql("CREATE TABLE IF NOT EXISTS BOYS (name,dorm,data)");
      for (var i=0, boy; boy = boys[i]; i++) {
        tx.executeSql("INSERT INTO BOYS (name, dorm, data) values (?,?,?)", [boy.name, boy.dorm, boy.data]);
      }
    }, function(tx, err) {database.errorHandler(tx,err);failure();}, success);
  },
  
  setHelpers: function (helpers, success, failure) {
    db.transaction(function(tx) {
      tx.executeSql("DROP TABLE HELPERS");
      tx.executeSql("CREATE TABLE IF NOT EXISTS HELPERS (name, dateofbirth, address, phonenumber, mobilenumber, email, notes)");
      for (var i=0, helper; helper = helpers[i]; i++) {
        tx.executeSql("INSERT INTO HELPERS (name, dateofbirth, address, phonenumber, mobilenumber, email, notes) values (?,?,?,?,?,?,?)",
            [helper["Name"], helper["Date of Birth"], helper["Address"], helper["Home Phone"], helper["Mobile"], helper["Email"], helper["Notes"]]);
      }
    }, function(tx, err) {database.errorHandler(tx,err);failure();}, success);
  },
  
  getHelpers: function(callback) {
    db.transaction(function(tx) {
      tx.executeSql('SELECT name, dateofbirth, address, phonenumber, mobilenumber, email, notes FROM HELPERS ORDER BY name', [], function(tx, results) {
        var numHelpers = results.rows.length;
        console.log("Found "+numHelpers+" helpers");
        var helpers = [];
        for (var i=0; i<numHelpers; i++) {
          var row = results.rows.item(i);
          helpers.push({
            name: row.name,
            dob: row.dateofbirth,
            address: row.address,
            phone: row.phonenumber,
            mobile: row.mobilenumber,
            email: row.email,
            notes: row.notes
          });
        }
        callback(helpers);
      }, database.errorHandler);
    });    
  },
  
  getHelper: function(name, callback) {
    db.transaction(function(tx) {
      tx.executeSql('SELECT name, dateofbirth, address, phonenumber, mobilenumber, email, notes FROM HELPERS WHERE name = ?', [name], function(tx, results) {
        if (results.rows.length === 1) {
          var row = results.rows.item(0);
          callback({
            name: row.name,
            dob: row.dateofbirth,
            address: row.address,
            phone: row.phonenumber,
            mobile: row.mobilenumber,
            email: row.email,
            notes: row.notes
          })
        }
      }, database.errorHandler);
    });    
  },
  
  errorHandler : function(tx, err) {
    console.log("Error processing SQL: "+err);
  },

  successHandler : function() {
    console.log("transaction success!");
  }
}