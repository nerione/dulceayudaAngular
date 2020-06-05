package com.example.demo.rsa;


public class RSAKey {
	
	public static final String RSA_PRIVATE_KEY = "-----BEGIN RSA PRIVATE KEY-----\r\n" + 
			"MIIEowIBAAKCAQEAziTJdbHwMSQ8+a8Osuo5Plf9NeQyPF+mSVtdTCCksx5ghRNK\r\n" + 
			"yY7TI/mYBfp+6q02fYtvyMoXzY/sn01el/vsBrhFfOK26NhAyHqVykj5jNsd/Wv4\r\n" + 
			"Eizc5VB/jq0fAaC0BcfB6tYvL+p7HZJQWSRKX8xvOSGJ6w2SFtHHWm8d6B5ak5Ro\r\n" + 
			"lsrub1bIpW88ShQMmAZxQOSzmXFzK6BPI9JuxPRTY+OdapkfO4InrjBDwvmbOZUH\r\n" + 
			"UmYkdU6gT/TrZHe5UPdSrsh5WD6vkGMNkhLzRZKhMdXUpGZIoSjs28DmgqtAWJXz\r\n" + 
			"o0Rof7O78uoePzB/k9fgD7nDc0p27vVXckveGQIDAQABAoIBADdKcqaYxV3I5Hnr\r\n" + 
			"1Dt0y0nHe+j6febnmMhQMyP6FXLdkPHgjMym/EbH6VihKW10bAscXS0z11KRadwI\r\n" + 
			"eni1X0cmIa0vefPWHPootwh8rkJzewW/8deeJNl0z+HK2WgsXFy4iXCdZiap87+o\r\n" + 
			"wNhH/ZLYWHSwZDehei+YsKm26ASbtEDcpWhunk6LsRkv7S7oZ3uvA/N9Awbngosw\r\n" + 
			"AOSElHWM0rrqOtO7XRiv9U2pQGczAxMXrSrXR/HXT5UX1artn6nJ/tOOxICld2zJ\r\n" + 
			"3BYsAYLbCDlIljXSDYtx190Ey17ndY1CS8g5Rntvxybf6GtkR0fU/dJz3azYbX/P\r\n" + 
			"RY8xZiECgYEA8DCGzFzrSW/kRyX9z9H/0L+jBTINKtrd8e2UWetc9tOVKc32LRTm\r\n" + 
			"BmNJQWnZZfa1EA8cW/NpfJe8qCil3saWDEubueQSdgDQgZRDTRCZT/HcSXuqcCFk\r\n" + 
			"tpyIeCI0RQjQrFeEeFziXcMcNtGRmhaTUQdkIVOURxS1z7Dlpp/Ewz0CgYEA27aM\r\n" + 
			"T2bREfI5N7SdV6GBDDiHxz+O0yp8LpGiV1BSYo+/VjZnsJ6LU4Gcg9nt3In0D5PB\r\n" + 
			"dG3sN2rvl8tkNrlFhASp6Ubx5d+K9NVWPIeIQ7f16W5UaUhed3YWK2YXCcGVEt9P\r\n" + 
			"22qpA9K5j7Gn/GCWJwDIXW29f9q4RV8FrhtVBA0CgYBfpOM4o9OppZ3AWzeLtvLo\r\n" + 
			"PDd9TSYDJ65ztQh7Ggcl3rEqOXLLFuRWLNkx7CV+vdPx9iJz1yTtUTr1t2H9qRR9\r\n" + 
			"BuqXBmP3i3F79/8ow/BY7lWrCUygZBvHv+Nz+yLXTqBR0HPWvh4B+jzU83TIaC73\r\n" + 
			"eDr+LlsARxXzA10fkcV3fQKBgQDXvKyTPrLle0GBmECzDitns45nL9pvMJVVKL0O\r\n" + 
			"HRl4HAvVfIBeJZ9K9MaZlQCBf5f9P6lCSLkcRU1TcGxLa6p9khLAs1/Mxq5r75rI\r\n" + 
			"vpAnZlf5yNLruhrAFFx6arkOZF2bdVi1a0rgLSb1mI0JPFHTpGoZiyr6gupTe5VZ\r\n" + 
			"Ow9EdQKBgDViEA+SED+A1kPXCFYeF3F7flhFGJvI2KvSTGzLyIosmG3Ot7dWOHVI\r\n" + 
			"QJj8Nr8LM9jZlB+hjXTQtaz9HTeF5x1v2U6RMmyPz9mnKKVp1OkNjABqz4S8j4+w\r\n" + 
			"Z6jK2Z4L2zfARR9fm2MHgrHAynUgGQKUrw2M+lAn6HVoGD45Hv7k\r\n" + 
			"-----END RSA PRIVATE KEY-----";
	
	public static final String RSA_PUBLIC_KEY = "-----BEGIN PUBLIC KEY-----\r\n" + 
			"MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAziTJdbHwMSQ8+a8Osuo5\r\n" + 
			"Plf9NeQyPF+mSVtdTCCksx5ghRNKyY7TI/mYBfp+6q02fYtvyMoXzY/sn01el/vs\r\n" + 
			"BrhFfOK26NhAyHqVykj5jNsd/Wv4Eizc5VB/jq0fAaC0BcfB6tYvL+p7HZJQWSRK\r\n" + 
			"X8xvOSGJ6w2SFtHHWm8d6B5ak5Rolsrub1bIpW88ShQMmAZxQOSzmXFzK6BPI9Ju\r\n" + 
			"xPRTY+OdapkfO4InrjBDwvmbOZUHUmYkdU6gT/TrZHe5UPdSrsh5WD6vkGMNkhLz\r\n" + 
			"RZKhMdXUpGZIoSjs28DmgqtAWJXzo0Rof7O78uoePzB/k9fgD7nDc0p27vVXckve\r\n" + 
			"GQIDAQAB\r\n" + 
			"-----END PUBLIC KEY-----";

}
