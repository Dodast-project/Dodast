# 📌 Dodast

دودست، خلاقیتی از واژه دست دوم است به منظور پلتفرم فروش کالای دست دوم توسط کاربران است. می توان آگهی ثبت کرد و با تایید مدیر، آن را به اشتراک گذاشت و حتی با فروشنده ها در ارتباط بود.  

---

# 👥 Team Members

| Name |
|------|
| رامان رضائی |
| هلیا نصیرلو |

---

# 📋 Project Overview

این پروژه با زبان برنامه نویسی جاوا درست شده که روش ذخیره سازی آن PostgreSQL با داکر می باشد. همچنین برای ارتباط frontend و backend از REST API استفاده شده است. در بخشی از پروژه از Hibernate برای ارتباط با پایگاه داده استفاده شده است. برای پیاده سازی Backend از فریم ورک Spring Boot بهره میبرد. برای frontend نیز از JavaFx و FXML استفاده میکند و زبان طراحی ظاهر این پروژه نیز از CSS استفاده میشود. برای مدیریت وابستگی های پروژه و buil کرد آن از Maven استفاده شده است.  

---

# ⚙️ Prerequisites

قبل از اجرای پروژه مطمئن باشید این ابزار را نصب کردید:

- Java JDK 17
- Maven
- PostgreSQL
- Docker

---

# 🚀 Project Setup

1. Clone the repository

```bash
git clone https://github.com/Dodast-project/Dodast
```

2. Enter the project directory

```bash
cd Dodast
```

3. Configure the database

SPRING_DATASOURCE_URL: jdbc:postgresql://postgres:5432/dodast_postgre
SPRING_DATASOURCE_USERNAME: dodast_postgre_username
SPRING_DATASOURCE_PASSWORD: dodast_postgre_password

4. Install project dependencies.

cd backend
mvn clean install

---

# 🖥️ Running the Frontend

Navigate to the frontend directory:

```bash
cd frontend
```

Run the frontend application:

```bash
mvn javafx:run
```
---

# ⚡ Running the Backend

Navigate to the backend directory:

```bash
docker compose up
```
or


```bash
cd backend
```

Run the backend server:

```bash
mvn spring-boot:run
```

---

# 💾 Data Storage

The project uses:

- Database: PostgreSQL
- ORM: Hibernate / JPA 

---

# ✨ Implemented Features

- احراز هوبت کاربر
- ورود و ثبت نام کاربر
- مدیریت آگهی ها
- سیستم جستجو
- آگهی های کاربر
- علاقه‌مندی های کاربر
- سیستم چت با فروشنده
- امتیازدهی به فروشنده 
- پنل ادمین 
- مدیریت آگهی ها توسظ ادمین


---

# 📂 Project Structure

```
project
│
├── backend
│
├── frontend
│
└── README.md
```

---

# 📸 Screenshots

## Login & Register

<img width="880" height="576" alt="Screenshot 1405-05-01 at 10 28 46 PM" src="https://github.com/user-attachments/assets/731ce546-1727-4527-a9bb-f5d2c5a6bbd5" />
<img width="893" height="591" alt="Screenshot 1405-05-01 at 10 13 08 PM" src="https://github.com/user-attachments/assets/05245e55-4e3a-419e-9c3c-801480d90c1c" />


## Home

<img width="888" height="595" alt="Screenshot 1405-05-01 at 10 48 48 PM" src="https://github.com/user-attachments/assets/4471b263-664d-4b8f-9923-b87d1494b5bd" />


## Admin Panel

<img width="887" height="595" alt="Screenshot 1405-05-01 at 11 24 48 PM" src="https://github.com/user-attachments/assets/48e7350d-086f-4857-b12f-705cc0c945bc" />

---

# 👨‍💻 Contribution of Team Members

### Raman Rezaei
Backend:
- پیاده سازی توکن JWT و وابستگی های آن
- پیاده سازی ورود و ثبت نام کاربر
- پیاده سازی سیستم جستجو
- پیاده سازی علاقه‌مندی های کاربر
- پیاده سازی شناسایی ادمین و دسترسی های او
- پیاده سازی آپلود تصویر و ذخیره سازی و بازیابی آن
Frontend: 
- پیاده سازی صفحه ورود و ثبت نام کاربر
- پیاده سازی صفحه اصلی و کارت های آگهی
- پیاده سازی ثبت و ویرایش و تغییر وضعیت آگهی توسط کاربر
- پیاده سازی جستجو
- پیاده سازی صفحه علاقه‌مندی و آگهی های من
- پیاده سازی پنل ادمین و مدیریت آگهی ها

### Helia Nasirloo
Backend
- پیاده سازی هسته اولیه آگهی
- پیاده‌سازی ساختار Controller، Service و DTOهای بخش آگهی
- پیاده سازی Exception های مربوط به آگهی
- پیاده سازی سیستم گفت و گو و پیام رسانی بین کاربران
- پیاده سازی سیستم امتیاز دهی به فروشنده
Frontend
- پیاده سازی صفحه لیست گفت و گو ها و صفحه چت
- پیاده سازی آیکون و صفحه امتیاز دهی به فروشنده

---
