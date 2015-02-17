/*
######################################################
# ORA_CODE_DOCS.JS
# VERSION: 1.01
# ORIGINAL BUILD DATE: 22nd January 2015
# COPYRIGHT ORACLE CORP 2015 [UNLESS STATED OTHERWISE]
######################################################
*/
/* Report suite set up */
function s_setAccount(){var sa=["oracledevall","docs","en-us"];sa[0]=(s_checkdev())?"oracledevall,oracledevdocs":"oracledocs";return sa;}
/* PrePlugins */
function s_prePlugins(s){s_oraVer(":docs",":1.01");}
/* postPlugins plus site functions */
function s_postPlugins(s){var arr=location.href.split("/");s.pageName=arr[3]+":";s.channel=arr[3]+":";for(i=4;i<=arr.length-1;i++){s.pageName+=arr[i]+((i==arr.length-1)?"":"/");}if(s.pageName.indexOf("index.html")!=-1){s.pageName=s.pageName.replace(/index\.html$/,"");}if(s.pageName.indexOf("index.htm")!=-1){s.pageName=s.pageName.replace(/index\.htm$/,"");}for(i=4;i<=arr.length-2;i++){s.channel+=arr[i]+((i==arr.length-2)?"":"/");}}function s_checkdev(){var isDev=false;var devSites=new Array();devSites=["-stage","us.oracle.com","-dev","-content","localhost","127.0.0.1"];var al=devSites.length;for(i=0;i<al;i++){if(location.host.indexOf(devSites[i])!=-1){isDev=true;i=al+1;}}return(isDev);}function s_oraVer(_s,_v){_v=_s+_v;oraVersion=(oraVersion.indexOf(_s)==-1)?oraVersion+_v:oraVersion.substr(0,oraVersion.indexOf(_s))+_v;}