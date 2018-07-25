var blinker = document.getElementById('blink');
hide();
function hide(){
	blinker.setAttribute('style','display:none');
	setTimeout(show, 800);
}
function show(){
	blinker.removeAttribute('style','display:show');
	setTimeout(hide, 800);
}
