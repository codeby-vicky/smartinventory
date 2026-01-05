# Smart Inventory Management (Android)

Smart Inventory is a modern Android application designed to help small businesses and individuals efficiently manage products, sales, and inventory levels with real-time insights. The project follows clean architecture principles and is built with scalability and maintainability in mind.

---

## 🚀 Features

* 🔐 Secure Login & Session Management
* 📦 Inventory Management (Add, View, Update Products)
* 📊 Dashboard with KPIs (Total Products, Low Stock)
* 🧾 Sales Recording & Sales History
* ⚠️ Low Stock Alerts & Notifications
* 📈 Smart Demand Prediction (Phase-based extension ready)
* ☁️ Local Persistence using Room (SQLite)
* 🎨 Clean and Modern Material Design UI

---

## 🛠️ Tech Stack

* **Language:** Java
* **Architecture:** MVVM (Model–View–ViewModel)
* **UI:** XML + Material Components
* **Database:** Room (SQLite)
* **Lifecycle Components:** LiveData, ViewModel
* **Build System:** Gradle
* **IDE:** Android Studio

---

## 📂 Project Structure

```
com.example.smartinventory
│
├── data
│   ├── dao
│   ├── entity
│   └── database
│
├── ui
│   ├── auth
│   ├── dashboard
│   ├── inventory
│   ├── sales
│   └── reports
│
├── viewmodel
├── util
└── MainActivity
```

---

## 📸 Screenshots

Create a folder named **screenshots/** in the project root and add the images listed below.

| Login                      | Dashboard                      | Inventory                      |
| -------------------------- | ------------------------------ | ------------------------------ |
| ![](screenshots/login.png) | ![](screenshots/dashboard.png) | ![](screenshots/inventory.png) |

| Add Product                      | Sales                      | Reports                      |
| -------------------------------- | -------------------------- | ---------------------------- |
| ![](screenshots/add_product.png) | ![](screenshots/sales.png) | ![](screenshots/reports.png) |

---

## ▶️ How to Run the Project

1. Clone the repository:

   ```bash
   git clone https://github.com/your-username/smart-inventory-android.git
   ```
2. Open the project in **Android Studio**
3. Let Gradle sync completely
4. Run the app on an Emulator or Physical Android Device

---

## 📦 APK Download (Releases)

You can download the latest APK from the **Releases** section of this repository.

**Steps to generate APK:**

```
Build → Build Bundle(s) / APK(s) → Build APK(s)
```

APK Location:

```
app/build/outputs/apk/debug/app-debug.apk
```

Upload this APK to GitHub:

* Go to **Releases → Create New Release**
* Tag: `v1.0`
* Title: `Smart Inventory v1.0`
* Upload `Smart-Inventory.apk`

---

## 📌 Future Enhancements

* Cloud Sync using Firebase
* Advanced Demand Forecasting Algorithms
* Role-based User Management
* Export Reports (PDF / Excel)
* Barcode & QR Code Integration

---

## 👨‍💻 Author

**Vignesh M N**
Android & Software Engineering IT Student

---

## ⭐ Acknowledgements

* Android Developers Documentation
* Material Design Guidelines
* Open-source Android Community

---

If you find this project useful, please ⭐ star the repository.
