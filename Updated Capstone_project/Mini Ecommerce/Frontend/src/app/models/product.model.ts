export interface Product {
  id?: number;
  name: string;
  description: string;
  category: string;
  manufacturer: string;
  price: number;
  quantity: number;
  uom: string;
  rating: number;
  imageUrl?: string;
  manufactureDate?: Date;
}