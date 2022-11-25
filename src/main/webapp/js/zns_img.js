window.onload = function () {
    var oImg = $('img');
    var aImg = oImg.getElementsByTagName('img');
    var oPrev = $('prev');
    var oNext = $('next');
    var iNow = 0;

    var lunboli = $("lunboli").getElementsByTagName('li');

    play(iNow);

    oNext.onclick = function () {
        if (iNow == aImg.length - 1) {
            iNow = 0;
            listyle();
            play(iNow);
        }
        else {
            iNow++;
            listyle();
            play(iNow);
        }
    };
    function listyle() {
        for (var i = 0; i < lunboli.length; i++) {
            lunboli[i].className = "";
        }
        lunboli[iNow].className = "activeli";
    }

    oPrev.onclick = function () {
        if (iNow == 0) {
            iNow = aImg.length - 1;
            listyle();
            play(iNow);
        }
        else {
            iNow--;
            listyle();
            play(iNow);
        }
    };

    for (var i = 0; i < lunboli.length; i++) {
        lunboli[i].onclick = function () {
            var index = this.id.slice(2);
            //console.log(index)
            for (var i = 0; i < lunboli.length; i++) {
                lunboli[i].className = "";
            }
            this.className = "activeli";
            iNow = index;
            play(iNow);

        }

    }
    function play(iNow) {
        for (var i = 0; i < aImg.length; i++) {
            startMove(aImg[i], { opacity: 0 });
            aImg[i].style.display = 'none';
        }
        aImg[iNow].style.display = 'block';
        startMove(aImg[iNow], { opacity: 100 });
    }


    //setTimeout(oNext.onclick, 3000)
    window.setInterval(oNext.onclick, 5000);


};


 