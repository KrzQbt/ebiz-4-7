package domain

import "gorm.io/gorm"

type Basket struct {
	gorm.Model
	BasketItems []BasketItem
	UserID      uint
}
