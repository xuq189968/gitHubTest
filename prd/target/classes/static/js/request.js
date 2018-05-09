var url=location.search;
var Request = new Object();
//?type=2&username=ok&pwd=ok
if(url.indexOf("?")!=-1)
{	//type=2&username=ok&pwd=ok
    var str = url.substr(1)
    strs = str.split("&");
    for(var i=0;i<strs.length;i++)
    {			//type					                   2
        Request[strs[i].split("=")[0]]=unescape(strs[i].split("=")[1]);
    }
}