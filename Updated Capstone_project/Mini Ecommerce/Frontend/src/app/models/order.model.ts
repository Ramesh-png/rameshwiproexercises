
export interface OrderItem {
  id?: number;
  productId: number;
  quantity: number;
  price: number;
  productName: string;
}

export interface Order {
  id?: number;
  userId: number;
  totalAmount: number;
  status: string;
  createdAt?: Date;
  orderItems: OrderItem[];
}