import { Component, OnInit } from '@angular/core';
import { MatIconModule } from '@angular/material/icon';
import { Recipe } from '../../types/recipe';
import { ActivatedRoute, Router, RouterModule } from '@angular/router';
import { RecipeService } from '../../services/recipe-service/recipe.service';
import { UserService } from '../../services/user.service';
import { DeleteRecipeComponent } from "../delete-recipe/delete-recipe.component";
import { RecipeRatingComponent } from '../recipe-rating/recipe-rating.component';
import { ReviewFormComponent } from '../review-form/review-form.component';

@Component({
    selector: 'app-recipe-details',
    standalone: true,
    templateUrl: './recipe-details.component.html',
    styleUrl: './recipe-details.component.css',
    imports: [MatIconModule, RouterModule, MatIconModule, DeleteRecipeComponent, RecipeRatingComponent, ReviewFormComponent]
})
export class RecipeDetailsComponent implements OnInit {

  recipe: Recipe | undefined;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private recipeService: RecipeService,
    public userService: UserService
  ) {}

  ngOnInit(): void {
    const recipeId: number = Number(this.route.snapshot.paramMap.get('id'));
    this.recipeService.getRecipeById(recipeId).subscribe(recipe => {
      this.recipe = recipe;
      recipe.steps = recipe.steps.sort((a, b) => a.stepNumber - b.stepNumber);
    })

  }

  addReview($event: Event) {
    throw new Error('Method not implemented.');
    }

}
