import { Step } from "./step";

export interface CreateRecipe {
    name: string,
    description: string,
    prepTimeMinutes: number,
    servings: number,
    categoryId: number,
    ingredients: any[],
    steps: any[],
}
