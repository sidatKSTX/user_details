# user_details

Retrieving data from excel file and inserting into postgres db

This project demonstrates on how to fetch data from an excel file and update it in the database
using postgres database

Used Swagger to upload the data and executed the application

ExcelReaderService contains **readExcelFile**

-This method is responsible for reading data from an Excel file (represented by a MultipartFile), parsing it, and
returning a list of UserInfo objects.

**XSSFWorkbook**: Used when you need to read from or write to an Excel workbook in the .xlsx format.

1. **MultipartFile** is designed to encapsulate the information and content of an uploaded file,
   such as its name, content type, size, and the actual content as an input stream.
2. You use MultipartFile as a parameter type in Spring MVC controller methods that handle file uploads.
3. When a client submits a form with a file input field,
   Spring automatically binds the uploaded file to a MultipartFile parameter in your controller method.

apache poi: We have to include this external library from maven repository in order to work on excel
and retrieve the excel sheet column information and then map it to the pojo (entity).

Removed Rate Field
Changed Exp field to String datatype
Added GetByID in the controller for bench profiles and daily submissions