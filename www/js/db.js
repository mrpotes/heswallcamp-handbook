var database = {
  db : null,
    
  initialise: function(callback) {
    db = window.openDatabase("Database", "1.0", "HDCHF Data", 2000000);
    db.transaction(function(tx) {
      tx.executeSql("CREATE TABLE IF NOT EXISTS CONFIG (key,value)");
      tx.executeSql("CREATE TABLE IF NOT EXISTS BOYS (name,data)");
      tx.executeSql("CREATE TABLE IF NOT EXISTS HELPERS (name, dateofbirth, address, phonenumber, notes)");
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
  
  errorHandler : function(tx, err) {
    alert("Error processing SQL: "+err);
  },

  successHandler : function() {
    console.log("transaction success!");
  }
}