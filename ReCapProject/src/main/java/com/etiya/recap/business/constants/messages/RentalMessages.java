package com.etiya.recap.business.constants.messages;

public class RentalMessages {
	 public static final String Add = "Araç başarıyla kiralandı";
     public static final String Delete = "Kiralama silme işlemi gerçekleşti";
     public static final String Update = "Kiralama güncelleme işlemi gerçekleşti";
     public static final String GetAll = "Kiralama bilgilerini listeleme işlemi gerçekleşti.";
     public static final String GetById = "Kiralama bilgisi Id'ye göre listelendi.";
     public static final String DeliverCar = "Araba teslim edildi.Fatura Kesildi";
     public static final String ErrorIfCarIsNotAvailable =  "Malesef kiralama yapılamaz. Araç şuan müşteride";
     public static final String ErrorFindeksScore =  "Araç kiralamak için findeks skorunuz yeterli değil.";
     public static final String ErrorIfCreditCardIsWrong =  "Kredi Kartı Numaranızı Düzgün Giriniz.";
     public static final String ErrorIfCarIsNotAvailableToRent =  "Araç şuan müsait olmadığı için kiralanamaz.";
     public static final String ErrorMoneyIsNotEnoughToRentACar = "Hesabınızdaki Para yetersizdir,Araç kiralanamadı.";
}
