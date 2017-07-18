var dumpForm = function () {
       	var inputFieldTypes = ['TextField','TextArea','DatePicker','DropdownList','CheckboxCheckbox','CheckboxListCheckbox'];

       	var groups = {};
       	for (var i = inputFieldTypes.length; i >= 0 ; i-- ) {
       		var type = inputFieldTypes[i];
       		groups[type] = document.getElementsByClassName(type);
       	}
       	var message = "";
       	for (var i in groups) {
       		var a = groups[i];
       		for (var j=0; j < a.length; j++) {
       			//console.log('a[' + i + ', ' + j + '] = ' + a.item(j).id );
       			var e = a.item(j);

       			message += '' + e.id + '.type = ' + i + '\n';
       			message += '' + e.id + '.value = ' + e.value + '\n';
       			if (i.startsWith('Checkbox') ) {
       				message += '' + e.id + '.checked = ' + e.checked + '\n';
       			}
       		}
       	}
       	return message;
       }