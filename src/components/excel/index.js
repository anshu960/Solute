import * as XLSX from "xlsx";

export const convertToJson = (csv) => {
    var lines = csv.split("\n");
    var result = [];
    var headers = lines[1].split(",");
    for (var i = 2; i < lines.length; i++) {
      var obj = {};
      var currentline = lines[i].split(",");
      for (var j = 0; j < headers.length; j++) {
        if(headers[j]){
            obj[headers[j]] = (currentline[j]);
        }
      }
      
      result.push(obj);
    }

    return result; //JavaScript object
    //return JSON.stringify(result); //JSON
  }

  export const readFile = (f, callback) => {
    var name = f.name;
    const reader = new FileReader();
    reader.onload = (evt) => {
      // evt = on_file_select event
      /* Parse data */
      const bstr = evt.target.result;
      const wb = XLSX.read(bstr, { type: "binary" });
      /* Get first worksheet */
      const wsname = wb.SheetNames[0];
      const ws = wb.Sheets[wsname];
      /* Convert array of arrays */
      const data = XLSX.utils.sheet_to_csv(ws, { header: 1 });
      /* Update state */
      //console.log("Data>>>" + data);// shows that excel data is read
      //console.log(convertToJson(data)); // shows data in json format
      callback(convertToJson(data));
    };
    reader.readAsBinaryString(f);
  }