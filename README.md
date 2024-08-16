<h1 align="center">Securing Android App with Encrypted SharedPreferences</h1>

<p align="center">
    <a href="https://play.google.com/store/apps/details?id=com.sumino.issave.storysaver.photovideo.downloader">
      <img src="https://miro.medium.com/v2/resize:fit:828/format:webp/1*pA-q5kPuCdZ1VzLAyXxFBg.jpeg" width="1280" />
    </a>
  </p>
  
  
<div align="center">
<a href="https://t.me/banrossyn" target="_blank"><img src="https://img.shields.io/badge/Telegram-26A5E4.svg?logo=Telegram&logoColor=white"></a>
<a href="https://wa.me/+919694260426/" target="_blank"><img src="https://img.shields.io/badge/WhatsApp-25D366.svg?logo=WhatsApp&logoColor=white"></a>
<a href="https://www.linkedin.com/in/banrossyn/" target="_blank"><img src="https://img.shields.io/badge/LinkedIn-0A66C2.svg?logo=LinkedIn&logoColor=white"></a>
<a href="mailto:banrossyn@gmail.com"><img src="https://img.shields.io/badge/Gmail-EA4335.svg?logo=Gmail&logoColor=white"></a>
<a href=https://www.instagram.com/androidwithrossyn/><img alt="insta" src="https://img.shields.io/badge/Instagram-E4405F.svg?logo=Instagram&logoColor=white"></a>
<a href="https://medium.com/@banrossyn/securing-android-app-with-encrypted-sharedpreferences-8a5e98783485"><img alt="" src="https://img.shields.io/badge/Medium-000000.svg?logo=Medium&logoColor=white"></a>
</div>

### Generally data is saved in this way in Android devices.

```xml
<?xml version='1.0' encoding='utf-8' standalone='yes' ?>
<map>
    <boolean name="googleMapShow" value="true" />
    <int name="dateFormat" value="7" />
    <boolean name="isShowTemperature" value="true" />
    <boolean name="isShowLevelIndicator" value="false" />
    <string name="language">en</string>
    <int name="latLongFormat" value="2" />
    <boolean name="isShowDate" value="true" />
    <int name="selectedTemplate" value="0" />
    <boolean name="isShowShortAddress" value="true" />
    <boolean name="firstTime" value="false" />
    <boolean name="isShowLongAddress" value="true" />
    <boolean name="isShowLatLong" value="true" />
    <boolean name="isShowTime" value="true" />
    <int name="temperatureFormat" value="1" />
    <int name="timeFormat" value="8" />
    <int name="googleMapType" value="1" />
</map>
```

### But when we use Encrypted SharedPreferences.
```xml
<?xml version='1.0' encoding='utf-8' standalone='yes' ?>
<map>
    <string name="__androidx_security_crypto_encrypted_prefs_key_keyset__">12a901bc682e67316d97810d5c4ed741daaeddd8877e30137e1b60d4094b04a6ff0bd27c351334ece9568e0b6a3d4b0de494c5577cd71a614e4198c02b8ce62626579ff52740fb41336fe4b012861f5b0533668d2642db6c45025f4428cf6fcd1aba151e991a4e1785dbd0848c8ded29a7540ad8a31236fd40b8c4ec9fa08b5ddf07ed49d6ffb42dbfa98c1a9a06c929f46120bcc6c563e82714e2f4134915b989ed0e75036428bcec24d37d1a4408d2a9e1c304123c0a30747970652e676f6f676c65617069732e636f6d2f676f6f676c652e63727970746f2e74696e6b2e4165735369764b6579100118d2a9e1c3042001</string>
    <string name="AUh4VNJxfVMtKEShUAH66Y/Wd6tacepdmB51suathcQ+YCrsu4hZ0Xg=">ARpRaxiHZmU9NmpOYhXGsKFWh0Q5gfG1YtjOZ+/ET0vY/zU4Pe4=</string>
    <string name="AUh4VNJW7atm/QULrLCmY/d2aNzirJKQGNOA3WOPUb0=">ARpRaxipyG/6dldVpNFeXh4JEO+NjL6JxmH24TTQVBj1xDVt4YKpEK8=</string>
    <string name="AUh4VNJyHr2mLKGjNgD1KKyjYWNeUMCaUEwd9BTrIk/ycU9l">ARpRaxhaVtgbMw1OzeIB/+3Nywr2+DM4pzP8S9M3Jd0Qd3bvKtQXBN4=</string>
    <string name="__androidx_security_crypto_encrypted_prefs_value_keyset__">128801cb3c76b722706eeed2f367d681c440c160a4cad6d6279d87eb1ae86494ac213f596c289d9b677f1aff5d6152fbf777fe5e57319363213e1ad6dea868e104cc6c3772924516f3e6026e863dcf3a29547876e83c833e6d7a711c86fec53a9445c09bfbe47fb3bf3d3b5339ef9c80da7a7198a6c9a2736e9a55f2039a455ddaf7fce792200904df97921a440898d6c5d201123c0a30747970652e676f6f676c65617069732e636f6d2f676f6f676c652e63727970746f2e74696e6b2e41657347636d4b657910011898d6c5d2012001</string>
    <string name="AUh4VNLEMzO0ZEzywCdpX5nBzKTXdomqrA86p2Qz1hNg3DniJQw=">ARpRaxi7ULeVm2XWXKuSkU/0EzQKvdlou87SmUcBW4iaZMRvBgk=</string>
    <string name="AUh4VNLBzx1AEDT0DKye1uJvGOy0pDUu/+5L4EVNp6VeMFd7Ua8=">ARpRaxg8hP0mELpEOwh0nfVD6wd1K8FEt78EjzdWESkBk+nY0hE=</string>
    <string name="AUh4VNKRI5oaAyh3ukO1nrUvZOyznA8Wq1PUU0kZ1g==">ARpRaxi3ROeOi0fEMQ4i4Px+1ryY+adkx7aG5zBBWwD+9hPfv20=</string>
    <string name="AUh4VNJG2G6tiib4AgyC1Zyp+JB0fwinTJRyTkvooQ==">ARpRaxgClZXUx9+dAt8DPN/pWap8QTWm5cGiIMwB0OHDNPZJs2gMu60=</string>
    <string name="AUh4VNL4/Rv9MNzDJTM5VPM9seA71pP1sIqt13XGC5oSj60ToE/g">ARpRaxgk4Eg5Ssh8jV4w8BDdKjFDZ1pD3V6P6PJRtiJMpTXckmw=</string>
    <string name="AUh4VNLOvgbmqRDmi7BBk8+/1F/FJ7H6rsoNl2B3MBl/7g==">ARpRaxipnE2qdflE/X7dQYbNBtu7NxptAfmdz8+J/lBODO0kqSU=</string>
    <string name="AUh4VNK3nOPZuU3qgLFBAX1rs08LgLF3DKeCCbNK">ARpRaxghDuvQTvR+sH3qge7h9t7SZH4WITftmnA9jpZuizdDTzE=</string>
    <string name="AUh4VNJ6aLMPosbF9bIT1f8h3KkBDKsNjH3TNa/9">ARpRaxgTIDlUlcqBurlhjMIBiWdYd/Iv/XafyPYAX+4FbElm+b8yEys=</string>
</map>
```

Add the required dependencies to your build.gradle file:

```groovy
dependencies {
    implementation("androidx.security:security-crypto:1.1.0-alpha06")
}
```
```kotlin
  fun init(context : Context) {
   
        val masterKeyAlias = MasterKey.Builder(context)
            .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
            .build()

        this.preferences = EncryptedSharedPreferences.create(
            context,
            SHARED_PREFERENCE_FILE_NAME,
            masterKeyAlias,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )

    }
```





