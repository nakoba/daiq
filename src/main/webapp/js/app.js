var ajax = {};
ajax.send = function(url, callback, method, data, sync) {
    var x;
    x = new XMLHttpRequest();
    x.open(method, url, sync);
    x.onreadystatechange = function() {
        if (x.readyState == 4 && x.status == 200) {
            callback(JSON.parse(x.responseText))
        }
    };
    if (method == 'POST') {
        x.setRequestHeader("Content-Type","application/json");
    }
    x.send(data)
};
ajax.get = function(url, data, callback) {
    var query = [];
    for (var key in data) {
        query.push(encodeURIComponent(key) + '=' + encodeURIComponent(data[key]));
    }
    ajax.send(url + '?' + query.join('&'), callback, 'GET', null, true)
};
ajax.post = function(url, data, callback) {
    ajax.send(url, callback, 'POST', JSON.stringify(data), true)
};


function Each(obj, fn) {
	if (obj.length) for (var i = 0, ol = obj.length, v = obj[0]; i < ol && fn(v, i) !== false; v = obj[++i]);
	else for (var p in obj) if (fn(obj[p], p) === false) break;
};