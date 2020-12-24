/*获得url中指定参数的值*/
function GetQueryString(name) {
  var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
  var r = window.location.search.substr(1).match(reg);
  if (r != null) return decodeURIComponent(r[2]);
  return null;
}

/*判断空值*/
function isEmpty(str) {
  if (str == 'undefined' || !str || !/[^\s]/.test(str)) {
    //为空
    return true;
  } else {
    //不为空
    return false;
  }
}