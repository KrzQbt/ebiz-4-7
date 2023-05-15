package main

import (
	"fmt"
	"github.com/labstack/echo/v4"
	"github.com/labstack/echo/v4/middleware"
	"gorm.io/driver/mysql"
	"gorm.io/gorm"
	"react-shop-go/api"
	"react-shop-go/domain"
	"react-shop-go/service"
)

func main() {
	dsn := "root@tcp(127.0.0.1:3306)/reactshop?charset=utf8mb4&parseTime=True&loc=Local"
	db, err := gorm.Open(mysql.Open(dsn), &gorm.Config{})
	if err != nil {
		panic("failed to connect database")
	}

	// Migrate the schema
	db.AutoMigrate(
		domain.Product{},
	)
	products := []domain.Product{}
	db.Find(&products)
	if len(products) == 0 {
		fmt.Print("no products in db, creating")
		product1 := new(domain.Product)
		product1.Name = "Car"
		product1.Description = "A car"
		db.Create(&product1)

		product2 := new(domain.Product)
		product2.Name = "Bike"
		product2.Description = "A bike"
		db.Create(&product2)

	}

	fmt.Print(products)
	e := echo.New()
	e.Use(middleware.CORSWithConfig(middleware.CORSConfig{
		AllowOrigins: []string{"*"},
		AllowHeaders: []string{echo.HeaderOrigin, echo.HeaderContentType, echo.HeaderAccept},
	}))
	e.Use(service.SetDBGormContext(db))
	api.AddRoutesToContext(e)

	e.Logger.Fatal(e.Start(":1323"))
}
