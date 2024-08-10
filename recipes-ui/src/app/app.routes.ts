import { Routes } from '@angular/router';
import { LoginComponent } from './components/login/login.component';
import { RegisterComponent } from './components/register/register.component';
import { CreateRecipeComponent } from './components/create-recipe/create-recipe.component';
import { RecipeDetailsComponent } from './components/recipe-details/recipe-details.component';
import { EditRecipeComponent } from './components/edit-recipe/edit-recipe.component';
import { HomePageComponent } from './components/home-page/home-page.component';
import { NotFoundComponent } from './components/not-found/not-found.component';

export const routes: Routes = [
    { path: 'login', component: LoginComponent },
    { path: 'register', component: RegisterComponent },
    { path: 'add-recipe', component: CreateRecipeComponent },
    { path: 'recipe/:id/edit', component: EditRecipeComponent },
    { path: 'recipe/:id', component: RecipeDetailsComponent },
    { path: '', component: HomePageComponent },
    { path: '404', component: NotFoundComponent },
    { path: '**', redirectTo: '/404' }
];
