Demo site: https://git.heroku.com/fierce-river-35208.git

Bu proje, kullanıcı tarafından sağlanan bir dizi tamsayı ve metin üzerinde işlem yaparak yeni bir metin oluşturan bir Java web uygulamasını içerir.

App.java dosyası, kullanıcı tarafından sağlanan tamsayılarla ve metinlerle bir algoritma çalıştırır. Bu algoritma, her bir metin öğesi için tamsayılarla yapılan işlemler sonucunda yeni metinler oluşturur. Bu işlem, her metin için ayrı ayrı gerçekleştirilir ve sonuçlar bir ArrayList içinde saklanır.

Kod, kullanıcı tarafından sağlanan tamsayılarla metinler üzerinde basit bir dönüşüm gerçekleştirir. Metin içindeki karakterlerin ASCII değerlerini belirli bir sayı ile (Integer Arraylist'lerinin doğru indexteki intlerin toplanmasıyla elde edilen değer) kaydırarak yeni bir metin oluşturur. Sayı, tamsayılarla yapılan işlemlere ve karakterin konumuna bağlı olarak (elde edilen değer (charın indexi*parametrer value)) değişir.

AppTest.java dosyası, App sınıfındaki algoritmanın doğruluğunu test etmek için çeşitli birim testlerini içerir.

Projeyi çalıştırmak için, Maven ve Java JDK 8 veya üstü gereklidir. Proje, kullanıcıların web tarayıcılarını kullanarak algoritmayı doğrudan deneyimleyebilecekleri bir web arayüzü sunar.

Bu proje, Travis CI ve Heroku entegrasyonları ile sürekli entegrasyon ve otomatik dağıtım için hazırdır.

[![Build Status](https://app.travis-ci.com/aylinbrtc/myDemoApp.svg?token=zsUqFb3HbguQjV6DMTxg&branch=master)](https://app.travis-ci.com/aylinbrtc/myDemoApp)
