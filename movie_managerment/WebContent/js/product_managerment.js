var snack = $("#snack");
var drink = $("#drink");
var gift = $("#gift");
var enroll = $("#enroll");

snack.click(function () {
    $(".snack_product").css("display", "block");
    $(".drink_product").css("display", "none");
    $(".gift_product").css("display", "none");
    $(".product_enroll").css("display", "none");
});

drink.click(function () {
    $(".snack_product").css("display", "none");
    $(".drink_product").css("display", "block");
    $(".gift_product").css("display", "none");
    $(".product_enroll").css("display", "none");
});

gift.click(function () {
    $(".snack_product").css("display", "none");
    $(".drink_product").css("display", "none");
    $(".gift_product").css("display", "block");
    $(".product_enroll").css("display", "none");
});
enroll.click(function () {
    $(".snack_product").css("display", "none");
    $(".drink_product").css("display", "none");
    $(".gift_product").css("display", "none");
    $(".product_enroll").css("display", "block");
});