package domain

import "gorm.io/gorm"

type BasketItem struct {
	gorm.Model
	ProductID uint
	BasketID  uint
	Amount    uint
}
