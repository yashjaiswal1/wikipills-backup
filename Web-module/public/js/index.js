// Removes pop-up banner
function delBanner(){
    var item = document.getElementById("ofBar");
    console.log(item);
    item.parentNode.removeChild(item);
  }