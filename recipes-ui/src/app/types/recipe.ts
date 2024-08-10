import { Category } from "./category";
import { Ingredient } from "./ingredient";
import { Step } from "./step";

export interface Recipe {
    id: number,
    name: string,
    description: string,
    prepTimeMinutes: number,
    servings: number,
    imageUrl: string,
    userId: number,
    createdAt: string,
    lastModifiedAt: string,
    category: Category,
    steps: Step[],
    ingredients: Ingredient[]
}
