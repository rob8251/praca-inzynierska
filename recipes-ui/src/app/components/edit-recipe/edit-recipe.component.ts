import { Component, Input, OnInit } from '@angular/core';
import { RecipeFormComponent } from '../recipe-form/recipe-form.component';
import { Recipe } from '../../types/recipe';
import { ActivatedRoute, Router } from '@angular/router';
import { RecipeService } from '../../services/recipe-service/recipe.service';
import { CreateRecipe } from '../../types/create-recipe';

@Component({
  selector: 'app-edit-recipe',
  standalone: true,
  imports: [RecipeFormComponent],
  templateUrl: './edit-recipe.component.html',
  styleUrl: './edit-recipe.component.css'
})
export class EditRecipeComponent implements OnInit {
  recipeToEdit!: Recipe;
  @Input() recipe!: CreateRecipe;


  constructor(private recipeService: RecipeService, private route: ActivatedRoute, private router: Router) {}

  ngOnInit(): void {
    const recipeId = Number(this.route.snapshot.paramMap.get('id'));
    this.recipeService.getRecipeById(recipeId).subscribe(recipe => {
      this.recipeToEdit = recipe;
    });
  }

  editRecipe(recipe: CreateRecipe) {
    this.recipeService.editRecipe(this.recipeToEdit.id, recipe).subscribe(() => {
      this.router.navigate([`/recipe/${this.recipeToEdit.id}`]);
    });
  }

}
