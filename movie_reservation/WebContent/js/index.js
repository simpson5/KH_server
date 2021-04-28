var $left_icon = $(".fa-angle-left");
var $right_icon = $(".fa-angle-right");

$left_icon.click(prevImg);
$right_icon.click(nextImg);

// setInterval(nextImg, 5000);

var index = 2;
var slide_witdh = window.innerWidth;
console.log(window.innerWidth);
var img_position = [- 2 * slide_witdh, - 1 * slide_witdh, 0, 1 * slide_witdh, 2 * slide_witdh];

function nextImg() {
  index = index + 1;
  for (var i = 0; i < 5; i++) {
    var currPoster = $("#screen-poster" + i);
    img_position[i] = img_position[i] - slide_witdh;
    if (img_position[i] < - 2 * slide_witdh) {
      $(currPoster).css({ "left": "200vw" });
      img_position[i] = 2 * slide_witdh;
    }
    $(currPoster).animate(
      {
        left: img_position[i],
      },
      500,
      "swing",
      function () { }
    );
    console.log(i + " : " + img_position[i]);
  }
}

function prevImg() {
  index = index - 1;
  for (var i = 0; i < 5; i++) {
    var currPoster = $("#screen-poster" + i);
    img_position[i] = img_position[i] + slide_witdh;
    if (img_position[i] > 2 * slide_witdh) {
      $(currPoster).css({ "left": "-200vw" });
      img_position[i] = -2 * slide_witdh;
    }
    $(currPoster).animate(
      {

        left: img_position[i],
      },
      500,
      "swing",
      function () { }
    );
    console.log(i + " : " + img_position[i]);
  }
}
