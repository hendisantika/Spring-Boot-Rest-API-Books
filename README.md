# Spring-Boot-Rest-API-Books
Spring-Boot-Rest-API-Books


### To run this project please follow the instructions below :
`mvn clean spring-boot:run`


It is simple Spring Boot Rest service CRUD operation example using MySQL  database.

1. Import project in Netbeans (recommended), Eclipse/Spring STS as Maven Project.

2. Run project as Spring Boot Application or deploy it on Tomcat or any Application Server.

3. Open your terminal and run this command : mvn clean spring-boot:run

  No need to create DB. It creates automatically by Spring Boot.

4. Execute SQL Files to insert data into your MySQL Database.

5. Open Postman rest client or other rest client.

6. Set Content type as application/json.


List API's :

1. Show All Books :

Get Request

`URL: http://localhost:8080/api/buku`

Method: GET
``` 
[
  {
    "bookId": "B001",
    "title": "30 Hari Mencari Cinta",
    "category": "Novel",
    "registered": "2016-09-04",
    "total": 1,
    "available": 0,
    "peminjaman": []
  },
  {
    "bookId": "B002",
    "title": "30 Hari Mencari Jodoh",
    "category": "Novel",
    "registered": "2016-09-04",
    "total": 1,
    "available": 0,
    "peminjaman": []
  },
  {
    "bookId": "B003",
    "title": "30 Hari Mencari Rezeki",
    "category": "Novel",
    "registered": "2016-09-04",
    "total": 1,
    "available": 0,
    "peminjaman": []
  },
  {
    "bookId": "B004",
    "title": "30 Hari Mahir Java",
    "category": "Pemrograman",
    "registered": "2016-09-04",
    "total": 1,
    "available": 0,
    "peminjaman": []
  },
  {
    "bookId": "B005",
    "title": "30 Hari Mahir PHP",
    "category": "Pemrograman",
    "registered": "2016-09-04",
    "total": 1,
    "available": 0,
    "peminjaman": []
  },
  {
    "bookId": "B006",
    "title": "30 Hari Mahir C++",
    "category": "Pemrograman",
    "registered": "2016-09-04",
    "total": 1,
    "available": 0,
    "peminjaman": []
  },
  {
    "bookId": "B007",
    "title": "30 Hari Mahir C++",
    "category": "Pemrograman",
    "registered": "2016-09-04",
    "total": 2,
    "available": 0,
    "peminjaman": []
  },
  {
    "bookId": "B008",
    "title": "30 Hari Mahir C++",
    "category": "Pemrograman",
    "registered": "2016-09-04",
    "total": 3,
    "available": 0,
    "peminjaman": []
  },
  {
    "bookId": "B009",
    "title": "30 Hari Mahir Java",
    "category": "Pemrograman",
    "registered": "2016-09-04",
    "total": 2,
    "available": 0,
    "peminjaman": []
  },
  {
    "bookId": "B010",
    "title": "30 Hari Mahir Java",
    "category": "Pemrograman",
    "registered": "2016-09-04",
    "total": 3,
    "available": 0,
    "peminjaman": []
  },
  {
    "bookId": "B011",
    "title": "30 Hari Mahir C++",
    "category": "Pemrograman",
    "registered": "2016-09-04",
    "total": 4,
    "available": 0,
    "peminjaman": []
  }
]
```

2. Get Post Request to Add Book

`URL: http://localhost:8080/api/buku`

Method: POST
```$xslt
{
      "bookId": "B011",
      "title": "30 Hari Mahir C++",
      "category": "Pemrograman"
}
```


3. Filter the Book Collections :

`URL: http://localhost:8080/api/buku/filter/title/category`

Method : POST

Request :
```$xslt
{     
      "title": "30 Hari Mahir C++",
      "category": "Pemrograman"
}
```

Reponse :
```$xslt

{
  "Payload": [
    {
      "bookId": "B006",
      "title": "30 Hari Mahir C++",
      "category": "Pemrograman",
      "registered": "2016-09-04",
      "total": 1,
      "available": 0,
      "peminjaman": []
    },
    {
      "bookId": "B007",
      "title": "30 Hari Mahir C++",
      "category": "Pemrograman",
      "registered": "2016-09-04",
      "total": 2,
      "available": 0,
      "peminjaman": []
    },
    {
      "bookId": "B008",
      "title": "30 Hari Mahir C++",
      "category": "Pemrograman",
      "registered": "2016-09-04",
      "total": 3,
      "available": 0,
      "peminjaman": []
    },
    {
      "bookId": "B011",
      "title": "30 Hari Mahir C++",
      "category": "Pemrograman",
      "registered": "2016-09-04",
      "total": 4,
      "available": 0,
      "peminjaman": []
    }
  ],
  "message": "Buku ditemukan!",
  "Success": "True"
}
```

4.  Add New Borrower :

Method : POST
```$xslt

http://localhost:8080/api/peminjam

{
      "uid": "U008",
      "name" : "Namikaze Minato",
      "address" : "Konohagakure"
     
}
```


5. Add Transaction

Method Post :

`URL : http://localhost:8080/api/peminjaman2`

Request :
```$xslt

{
     
      "bookId": "B003",
      "userId" : "U003"
}
```


Response :
```$xslt

{
  "Payload": {
    "id": 6,
    "startDt": "2016-09-04",
    "returnDt": "2016-09-13",
    "bookId": "B003",
    "userId": "U003"
  },
  "message": "Data Peminjaman sukses",
  "Success": "True"
}
```


6.  See History Transaction :

Method :  POST

`URL : http://localhost:8080/api/peminjaman2/find`

Request :
```$xslt

{     
      "bookId": "B003",
      "userId" : "U003"
}
```

Response :
```$xslt

{
  "Payload": [
    {
      "id": 1,
      "startDt": "2016-09-04",
      "returnDt": "2016-09-13",
      "bookId": "B002",
      "userId": "U002"
    },
    {
      "id": 2,
      "startDt": "2016-09-04",
      "returnDt": "2016-09-13",
      "bookId": "B002",
      "userId": "U001"
    },
    {
      "id": 3,
      "startDt": "2016-09-04",
      "returnDt": "2016-09-13",
      "bookId": "B002",
      "userId": "U003"
    },
    {
      "id": 4,
      "startDt": "2016-09-04",
      "returnDt": "2016-09-13",
      "bookId": "B003",
      "userId": "U003"
    },
    {
      "id": 5,
      "startDt": "2016-09-04",
      "returnDt": "2016-09-13",
      "bookId": "B003",
      "userId": "U003"
    },
    {
      "id": 6,
      "startDt": "2016-09-04",
      "returnDt": "2016-09-13",
      "bookId": "B003",
      "userId": "U003"
    }
  ],
  "message": "Data Peminjaman berhasil ditemukan",
  "Success": "True"
}
```
