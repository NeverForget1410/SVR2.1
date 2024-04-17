$(document).on("click", ".open-modal", function () {
    var idDevice = $(this).data("idDevice");
    console.log("devices id=", idDevice);
    console.log(event.detail.value)
    $.ajax({
        type: "GET",
        url: `/api/devices/deviceDetails/${idDevice}`,
        success: function (data) {
            // Ustawia dane o sklepie w modalu
            $("#deviceRangeWeightMax").text(data.deviceRangeWeightMin);
            $("#deviceRangeWeightMin").text(data.deviceRangeWeightMax);
            $("#deviceValueLegalizationPlotE").text(data.deviceValueLegalizationPlotE);
            $("#deviceValuePlotReadingD").text(data.deviceValuePlotReadingD);
            $("#deviceBorderRangeTare").text(data.deviceBorderRangeTare);
            $("#deviceConformity").text(data.deviceConformity);
            $("#deviceDescryption").text(data.deviceDescryption);
            $("#weightClassName").text(data.weightClass.weightClassName);
            $("#cityName").text(data.store.address.city.cityName);
            $("#addressStreet").text(data.store.address.addressStreet);
            $("#addressNo").text(data.store.address.addressNo);
            $("#cityZipCode").text(data.store.address.cityZipCode);
            $("#storeName").text(data.store.storeName);
            $("#storeNo").text(data.store.storeNo);

        },
        error: function(xhr, status, error) {
            console.error("Błąd AJAX:", status, error);
            console.log("Szczegóły błędu:", xhr.responseText);
            alert("Wystąpił błąd podczas pobierania danych o sklepie.");
        }
    });
});
