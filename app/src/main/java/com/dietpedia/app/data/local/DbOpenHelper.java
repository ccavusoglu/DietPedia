package com.dietpedia.app.data.local;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.dietpedia.app.domain.model.Category;
import com.dietpedia.app.domain.model.Diet;
import com.dietpedia.app.domain.model.DietDetail;
import hugo.weaving.DebugLog;

/**
 * Created by Çağatay Çavuşoğlu on 22.06.2016.
 */
public class DbOpenHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "dietpedia.db";
    public static final int DATABASE_VERSION = 1;

    @DebugLog
    public DbOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    @DebugLog
    public void onCreate(SQLiteDatabase db) {
        db.beginTransaction();
        try {
            Db.CategoryTable.onCreate(db);
            Db.DietTable.onCreate(db);
            Db.DietDetailTable.onCreate(db);

            // TODO: read all from json
            long categoryId = db.insert(Db.CategoryTable.TABLE_NAME, null, new Category.Builder()
                    .name("Shock Diets")
                    .info("Simple and effective diets")
                    .order(1)
                    .build());

            //region 15 kg in 15 days
            long dietId = db.insert(Db.DietTable.TABLE_NAME, null, new Diet.Builder()
                    .name("15 Kg in 15 Days")
                    .categoryId(categoryId)
                    .info("If you have more excess weight, this diet will work much better for you. \n\n" +
                                  "Every Monday take following foods and beverages. \n" +
                                  "Breakfast: 1 cup of lemon juice without sugar.\n" +
                                  "Lunch: 1 apple and 1 toast.\n" +
                                  "Supper: 1 boiled egg, 1 tomato and 1 toast. \n\n" +
                                  "Consume lots of water during the diet. You will see the very first outcomes in 5 days. \n" +
                                  "During the diet you shouldn't take any alcohol. \n" +
                                  "Consume your foods warm and cold. \n" +
                                  "Repeat the 5-day plan 3 times.")
                    .build());

            db.insert(Db.DietDetailTable.TABLE_NAME, null, new DietDetail.Builder()
                    .name("Day 1")
                    .dietId(dietId)
                    .breakfast("1 orange\n" +
                                       "1 toast\n" +
                                       "1 cup of tea (sugar-free)")
                    .lunch("1 orange\n" +
                                   "1 boiled egg\n" +
                                   "1 yogurt\n")
                    .dinner("2 tomatoes\n" +
                                    "2 boiled eggs\n" +
                                    "Lettuce salad")
                    .build());

            db.insert(Db.DietDetailTable.TABLE_NAME, null, new DietDetail.Builder()
                    .name("Day 2")
                    .dietId(dietId)
                    .breakfast("1 orange\n" +
                                       "1 toast\n" +
                                       "1 cup of tea (sugar-free)")
                    .lunch("1 orange\n" +
                                   "1 boiled egg\n" +
                                   "1 yogurt\n" +
                                   "1 cup of tea or coffee\n")
                    .dinner("125 g ground meat\n" +
                                    "1 tomato\n" +
                                    "1 orange\n" +
                                    "1 toast")
                    .build());

            db.insert(Db.DietDetailTable.TABLE_NAME, null, new DietDetail.Builder()
                    .name("Day 3")
                    .dietId(dietId)
                    .breakfast("1 orange\n" +
                                       "1 toast\n" +
                                       "1 cup of tea (sugar-free)")
                    .lunch("1 orange\n" +
                                   "1 boiled egg\n" +
                                   "1 yogurt\n" +
                                   "1 head of lettuce\n")
                    .dinner("125g minced meat\n" +
                                    "1 tomato\n" +
                                    "1 orange\n" +
                                    "1 cup of coffee or tea")
                    .build());

            db.insert(Db.DietDetailTable.TABLE_NAME, null, new DietDetail.Builder()
                    .name("Day 4")
                    .dietId(dietId)
                    .breakfast("1 orange\n" +
                                       "1 toast\n" +
                                       "1 cup of tea (sugar-free).")
                    .lunch("125 g of fresh cheese.\n" +
                                   "1 orange.\n" +
                                   "1 toast.\n")
                    .dinner("125g minced meat.\n" +
                                    "2 tomatoes.\n" +
                                    "1 apple.\n" +
                                    "1 toast.")
                    .build());

            db.insert(Db.DietDetailTable.TABLE_NAME, null, new DietDetail.Builder()
                    .name("Day 5")
                    .dietId(dietId)
                    .breakfast("1 orange\n" +
                                       "1 toast\n" +
                                       "1 cup of tea (sugar-free).")
                    .lunch("200 g of prepared meat or fish.\n" +
                                   "1 tomato.\n")
                    .dinner("1 toast 0.5 kg prepared vegetables (carrots, cauliflower).\n" +
                                    "1 egg.")
                    .build());

            //endregion

            //region 5 kg in 3 days
            dietId = db.insert(Db.DietTable.TABLE_NAME, null, new Diet.Builder()
                    .name("5 Kg in 3 Days")
                    .categoryId(categoryId)
                    .info("Follow this diet only for 3 days to lose up to 5 kg. You may repeat it after 2 months. \n\n" +
                                  "Drink 3 lt of water every day. \n" +"" +
                                  "Do running exercise for 30 minutes every day.\n\n" +
                                  "Repeat the same plan for each day.")
                    .build());

            db.insert(Db.DietDetailTable.TABLE_NAME, null, new DietDetail.Builder()
                    .name("Day 1/2/3")
                    .dietId(dietId)
                    .breakfast("Half a glass of warm water with half a lemon\n" +
                                       "Later, 1 cup of black tea or herbal tea or coffee without sugar\n" +
                                       "1 cucumber\n" +
                                       "1 leaf of lettuce\n" +
                                       "Half bunch of parsley\n" +
                                       "Half bunch of rocket and 2 boiled eggs")
                    .lunch("1 yogurt\n" +
                                   "1 cucumber\n" +
                                   "Any amount of greens and 1 bowl of fat-free soup")
                    .dinner("1 bowl of fat-free vegetable soup\n" +
                                    "1 yogurt and an apple")
                    .snack1("One apple")
                    .build());

            //endregion

            //region 3 kg in 7 days
            dietId = db.insert(Db.DietTable.TABLE_NAME, null, new Diet.Builder()
                    .name("3 Kg in 7 Days")
                    .categoryId(categoryId)
                    .info("7 day plan to lose up to 3 kg. \n\n" +
                                  "Repeat same plan for every day.\n" +
                                  "Do fast-paced walking for 30 minutes every day after dinner.")
                    .build());

            db.insert(Db.DietDetailTable.TABLE_NAME, null, new DietDetail.Builder()
                    .name("Day 1")
                    .dietId(dietId)
                    .info("Dinners should be had by 6:00 PM every day.")
                    .breakfast("1 cup of green tea (sugar-free)\n" +
                                       "3 boiled eggs\n" +
                                       "Any amount of greens")
                    .lunch("Chicken salad\n" +
                                   "Fat-free tuna salad\n" +
                                   "Greens salad" +
                                   "100 g of boiled meat or fish\n" +
                                   "1 fat-free yogurt")
                    .dinner("Fat/Salt-free vegetable soup\n" +
                                    "Salad and fat-free boiled or raw vegetables")
                    .snack1("1 cup of fat-free milk\n" +
                                    "Handful of cereal")
                    .snack3("1 cup of green tea (sugar-free)\n" +
                                    "1 low calorie biscuit")
                    .build());

            //endregion

            //region 5 kg in 5 days
            dietId = db.insert(Db.DietTable.TABLE_NAME, null, new Diet.Builder()
                    .name("5 Kg in 5 Days")
                    .categoryId(categoryId)
                    .info("Follow this diet only for 5 days to lose up to 5 kg.\n\n" +
                                  "You may repeat it after 1 month break.")
                    .build());

            db.insert(Db.DietDetailTable.TABLE_NAME, null, new DietDetail.Builder()
                    .name("Day 1/2/3")
                    .dietId(dietId)
                    .info("Only boiled potatoes for every meal when you get hungry.")
                    .build());

            db.insert(Db.DietDetailTable.TABLE_NAME, null, new DietDetail.Builder()
                    .name("Day 4")
                    .dietId(dietId)
                    .breakfast("Boiled potato and an apple or orange")
                    .lunch("Boiled potato and an apple or orange")
                    .dinner("Boiled potato\n" +
                                    "An apple or orange and 1 boiled chicken drumstick")
                    .build());

            db.insert(Db.DietDetailTable.TABLE_NAME, null, new DietDetail.Builder()
                    .name("Day 5")
                    .dietId(dietId)
                    .breakfast("Boiled potato\n" +
                                       "An apple or orange and salad")
                    .lunch("Boiled potato\n" +
                                   "An apple or orange and salad")
                    .dinner("Boiled potato\n" +
                                    "An apple or orange and salad")
                    .build());

            //endregion

            //region 10 kg in 7 days
            dietId = db.insert(Db.DietTable.TABLE_NAME, null, new Diet.Builder()
                    .name("10 Kg in 7 Days")
                    .categoryId(categoryId)
                    .info("Follow this diet only for 7 days to lose up to 10 kg.\n\n" +
                                  "This diet is more effective for people with more excess weight.\n" +
                                  "This diet may be dangerous for your metabolism.\n" +
                                  "Please consult a dietitian before following.")
                    .build());

            db.insert(Db.DietDetailTable.TABLE_NAME, null, new DietDetail.Builder()
                    .name("Day 1/2/3/4/5/6/7")
                    .dietId(dietId)
                    .info("This plan includes fast-paced walking every morning.")
                    .breakfast("1 cup of water with lemon\n" +
                                       "After the walking workout\n" +
                                       "1 cup of coffee with milk")
                    .lunch("Boiled broccoli with lemon sauce\n" +
                                   "1 yogurt")
                    .dinner("1 green apple and 1 yogurt")
                    .snack3("7 almonds")
                    .build());

            //endregion

            categoryId = db.insert(Db.CategoryTable.TABLE_NAME, null, new Category.Builder()
                    .name("Popular Diets")
                    .info("Most general and well known diets")
                    .order(2)
                    .build());

            //region Deepika Diet
            dietId = db.insert(Db.DietTable.TABLE_NAME, null, new Diet.Builder()
                    .name("Deepika Diet")
                    .categoryId(categoryId)
                    .info("Lose your weight without starving.\n\n" +
                                  "Eat fresh fruits every 2 hours.")
                    .build());

            db.insert(Db.DietDetailTable.TABLE_NAME, null, new DietDetail.Builder()
                    .name("Day 1/2/3/4/5/6/7")
                    .dietId(dietId)
                    .breakfast("2 egg whites\n" +
                                       "Low fat milk")
                    .lunch("Fishes, generally grilled to avoid extra fat\n" +
                                   "Or vegetables.\n")
                    .dinner("Salad or vegetables")
                    .snack3("Almonds")
                    .snack4("Filter coffee")
                    .build());

            //endregion

            //region Fast Weight Loss
            dietId = db.insert(Db.DietTable.TABLE_NAME, null, new Diet.Builder()
                    .name("Fast Weight Loss")
                    .categoryId(categoryId)
                    .info("Follow this diet only for 7 days to lose up to 4 kg.\n\n" +
                                  "Wait 1 month before following this diet again.")
                    .build());

            db.insert(Db.DietDetailTable.TABLE_NAME, null, new DietDetail.Builder()
                    .name("Day 1/2/3/4/5/6/7")
                    .dietId(dietId)
                    .info("It's important to have breakfast very early on this diet.")
                    .breakfast("1 slice of low fat cheese\n" +
                                       "2 slices of wholegrain bread\n" +
                                       "Lettuce, parsley and rocket with lots of lemon")
                    .lunch("Any low-fat vegetable dish\n" +
                                   "1 yogurt\n" +
                                   "Green salad")
                    .dinner("Low fat soup\n" +
                                    "1 slice of wholemeal bread\n" +
                                    "Any low-fat vegetable dish\n" +
                                    "Green salad\n" +
                                    "1 low-fat yogurt\n")
                    .snack1("Green tea")
                    .snack2("4 dried apricot")
                    .snack3("Green tea")
                    .snack4("1 fruit")
                    .snack5("Green tea")
                    .build());

            //endregion

            //region South Beach Diet
            dietId = db.insert(Db.DietTable.TABLE_NAME, null, new Diet.Builder()
                    .name("South Beach Diet")
                    .categoryId(categoryId)
                    .info("First 15 days follow Day 1, then follow Day 2.\n" +
                                  "Once in a week you can eat any desert after dinner.\n" +
                                  "First 15 days, don't drink milk and eat yogurt.")
                    .build());

            db.insert(Db.DietDetailTable.TABLE_NAME, null, new DietDetail.Builder()
                    .name("Day 1")
                    .dietId(dietId)
                    .breakfast("1 cup of veggie smoothie\n" +
                                       "Omelet with mushroom\n" +
                                       "2 slices of ham\n" +
                                       "Decaf and sugar-free tea or coffee")
                    .lunch("Lettuce Salad\n" +
                                   "Grilled chicken breast")
                    .dinner("Boiled broccoli and cauliflower\n" +
                                    "Grilled beef with tomato")
                    .snack1("1 slice of low-fat cheese")
                    .snack3("1 cucumber\n" +
                                    "1 slice of low-fat cheese")
                    .build());

            db.insert(Db.DietDetailTable.TABLE_NAME, null, new DietDetail.Builder()
                    .name("Day 2")
                    .dietId(dietId)
                    .breakfast("1 bowl of yogurt with fruits")
                    .lunch("Chicken salad")
                    .dinner("Grilled meat\n" +
                                    "1 yogurt\n" +
                                    "Boiled vegetables")
                    .snack1("Grilled cheese")
                    .snack3("1-2 fruits")
                    .build());

            //endregion

            //region Mediterranean Diet
            dietId = db.insert(Db.DietTable.TABLE_NAME, null, new Diet.Builder()
                    .name("Mediterranean Diet")
                    .categoryId(categoryId)
                    .info("Follow this diet for 2 weeks.\n\n" +
                                  "Don't drink tea or coffee on an empty stomach.\n" +
                                  "No sugar of all kind.\n" +
                                  "Red meat once in a month.")
                                  .build());

            db.insert(Db.DietDetailTable.TABLE_NAME, null, new DietDetail.Builder()
                    .name("Week 1")
                    .dietId(dietId)
                    .breakfast("1 slice of wholemeal bread\n" +
                                       "50 g of low-fat cheese\n" +
                                       "6 olives\n" +
                                       "Sugar-free tea")
                    .lunch("100 g grilled fish or chicken\n" +
                                   "1 slice of wholemeal bread\n" +
                                   "Green Salad\n" +
                                   "1 fruit")
                    .dinner("Any low-fat vegetable dish\n" +
                                    "Green salad\n" +
                                    "1 fruit")
                    .snack1("1 yogurt\n" +
                                    "2 low calorie biscuits")
                    .snack3("Tea, coffee or any diet drink\n" +
                                    "4 low calorie biscuit or 1 slice of bread\n" +
                                    "50 g low-fat cheese")
                    .snack5("1 cup of fat-free milk, 2 low calorie biscuit")
                    .build());

            db.insert(Db.DietDetailTable.TABLE_NAME, null, new DietDetail.Builder()
                    .name("Week 2")
                    .dietId(dietId)
                    .breakfast("2 slices of wholegrain break\n" +
                                       "1 cup of fat-free milk\n" +
                                       "1 fruit\n" +
                                       "5 olives")
                    .lunch("1 canned bean\n" +
                                   "2 slices of wholemeal bread\n" +
                                   "1 yogurt\n" +
                                   "Half a portion vegetable dish\n" +
                                   "1-2 fruit")
                    .dinner("Grilled fish or chicken\n" +
                                    "100 g baked potato\n" +
                                    "2 slices of wholemeal bread\n" +
                                    "Raw vegetables\n" +
                                    "1-2 fruit")
                    .build());

            //endregion

            //region Rihanna Diet
            dietId = db.insert(Db.DietTable.TABLE_NAME, null, new Diet.Builder()
                    .name("Rihanna Diet")
                    .categoryId(categoryId)
                    .info("Eat fresh")
                    .build());

            db.insert(Db.DietDetailTable.TABLE_NAME, null, new DietDetail.Builder()
                    .name("Day 1/2/3")
                    .dietId(dietId)
                    .info("You can have total of 3 snacks between meals with total calories for each not exceeding 100 calories")
                    .breakfast("Fresh tomato\n" +
                                       "Cheese\n" +
                                       "Egg\n" +
                                       "Cucumber")
                    .lunch("Low-fat chicken\n" +
                                   "Green salad")
                    .dinner("Any low-fat vegetable dish\n" +
                                    "Salad\n" +
                                    "Yogurt")
                    .build());

            //endregion

            categoryId = db.insert(Db.CategoryTable.TABLE_NAME, null, new Category.Builder()
                    .name("Slimming Diets")
                    .info("Combine exercises with these diets and get in shape")
                    .order(3)
                    .build());

            //region Flat belly in 3 days
            dietId = db.insert(Db.DietTable.TABLE_NAME, null, new Diet.Builder()
                    .name("Flat Belly in 3 Days")
                    .categoryId(categoryId)
                    .info("Have a flat belly with this high protein low carb diet alongside with your daily exercises.")
                    .build());

            db.insert(Db.DietDetailTable.TABLE_NAME, null, new DietDetail.Builder()
                    .name("Day 1")
                    .dietId(dietId)
                    .breakfast("2 egg vegetable omelet")
                    .lunch("Tuna salad with or without grilled chicken pieces")
                    .dinner("150 g grilled chicken breast\n" +
                                    "Half boiled potato\n" +
                                    "Lots of salad")
                    .snack1("1 yogurt with blackberry, strawberry or cucumber")
                    .snack3("15-20 almonds")
                    .build());

            db.insert(Db.DietDetailTable.TABLE_NAME, null, new DietDetail.Builder()
                    .name("Day 2")
                    .dietId(dietId)
                    .breakfast("2 scrambled egg with half boiled potato")
                    .lunch("Spinach salad with tuna or grilled chicken")
                    .dinner("150 g grilled low-fat meat\n" +
                                    "1 bowl of boiled green beans")
                    .snack1("Smoothie with 1 cup of fat-free milk and 2 tablespoon of blackberry or strawberry")
                    .snack3("1 celery with 3 teaspoon of peanut butter")
                    .build());

            db.insert(Db.DietDetailTable.TABLE_NAME, null, new DietDetail.Builder()
                    .name("Day 3")
                    .dietId(dietId)
                    .breakfast("1 fat-free yogurt with fruits of the season")
                    .lunch("150 g boiled or grilled turkey\n" +
                                   "1 cup of boiled broccoli")
                    .dinner("150 g grilled fish\n" +
                                    "Cauliflower sauteed with 2 teaspoon of olive oil")
                    .snack1("15 almonds or 4-5 walnut")
                    .snack3("2 carrots")
                    .build());

            //endregion

            //region Flat belly in 7 days
            dietId = db.insert(Db.DietTable.TABLE_NAME, null, new Diet.Builder()
                    .name("Flat Belly in 7 Days")
                    .categoryId(categoryId)
                    .info("Lowering your stress level helps you lose weight around your belly.")
                    .build());

            db.insert(Db.DietDetailTable.TABLE_NAME, null, new DietDetail.Builder()
                    .name("Day 1")
                    .dietId(dietId)
                    .breakfast("2 slices of whole grain bread\n" +
                                       "1 boiled egg\n" +
                                       "1 slice of cheese\n" +
                                       "1 leaf of lettuce\n" +
                                       "1 slice of tomato\n" +
                                       "1/4 bowl of avocado slices")
                    .lunch("2 meatballs\n" +
                                   "Half a slice whole grain bread\n" +
                                   "1 tablespoon of curd cheese\n" +
                                   "1 tablespoon of olive oil")
                    .dinner("1/3 package wholemeal pasta\n" +
                                    "80 g grilled chicken breast\n" +
                                    "5-6 cherry tomato\n" +
                                    "Half a cup grated carrot\n" +
                                    "1 tablespoon of curd cheese")
                    .build());

            db.insert(Db.DietDetailTable.TABLE_NAME, null, new DietDetail.Builder()
                    .name("Day 2")
                    .dietId(dietId)
                    .breakfast(("1 cup of cereal\"" +
                            ("1 low-fat yogurt\n" +
                                    "Half a cup blackberry\n" +
                                    "2 tablespoon of almonds")))
                    .lunch("50 g grilled chicken breast\n" +
                                   "1 grated carrot\n" +
                                   "4 leaf of lettuce\n" +
                                   "1 grated carrot, 2 tablespoon of humus and 1 tablespoon of pine kernel mixed in a bowl")
                    .dinner("Vegetable burger with wholegrain bread including 1 tablespoon of mustardi 3 leaf of lettuce, half a tomato and 2 tablespoon of sliced onion")
                    .build());

            db.insert(Db.DietDetailTable.TABLE_NAME, null, new DietDetail.Builder()
                    .name("Day 3")
                    .dietId(dietId)
                    .breakfast("2-3 bowl boiled oat with 4-5 strawberry and 2 walnut")
                    .lunch("2 slices of wholegrain bread\n" +
                                   "2 tablespoon of olive spread\n" +
                                   "85 g salmon\n" +
                                   "Half a tomato\n" +
                                   "2 leaf of lettuce")
                    .dinner("50 g grilled chicken breast\n" +
                                    "Curd cheese salad")
                    .build());

            db.insert(Db.DietDetailTable.TABLE_NAME, null, new DietDetail.Builder()
                    .name("Day 4")
                    .dietId(dietId)
                    .breakfast("Half a cup of boiled oat with 2-3 strawberry, a banana, 2 little piece of bitter chocolate, 2 tablespoon of almonds")
                    .lunch("1/4 package wholemeal pasta\n" +
                                   "85 g grilled chicken breast\n" +
                                   "5-6 cherry tomato\n" +
                                   "2 grated carrot\n" +
                                   "2 tablespoon of grated parmesan")
                    .dinner("1 bowl of mixed greens\n" +
                                    "Half a canned green beans\n" +
                                    "2 green pepper\n" +
                                    "3/4 bowl of sweet corn\n" +
                                    "Half a sliced onion")
                    .build());

            db.insert(Db.DietDetailTable.TABLE_NAME, null, new DietDetail.Builder()
                    .name("Day 5")
                    .dietId(dietId)
                    .breakfast("1 slice of wholegrain bread with 2 tablespoon of hazelnut spread\n" +
                                       "1 tablespoon of dried grape")
                    .lunch("1 slice of wholegrain bread\n" +
                                   "80 g of tuna\n" +
                                   "4 tablespoon of grated parmesan")
                    .dinner("Thin bread pizza with tomato puree, 13 slices of pepperoni and parmesan")
                    .build());

            db.insert(Db.DietDetailTable.TABLE_NAME, null, new DietDetail.Builder()
                    .name("Day 6")
                    .dietId(dietId)
                    .breakfast("1 slice of wholegrain bread\n" +
                                       "1 tablespoon of ricotta cheese\n" +
                                       "2 tablespoon of walnut\n" +
                                       "1 apple")
                    .lunch("1 Meatball, half a bowl of spinach, 1 slices of green onion and sliced half an avocado within thin bread")
                    .dinner("100 g grilled salmon\n" +
                                    "Boiled sweet pea\n" +
                                    "2 tablespoon of almonds")
                    .build());

            db.insert(Db.DietDetailTable.TABLE_NAME, null, new DietDetail.Builder()
                    .name("Day 7")
                    .dietId(dietId)
                    .breakfast("Half a bowl of sliced spinach and sliced half an avocado mixed with egg white in thin bread")
                    .lunch("2 slices of wholegrain bread\n" +
                                   "1 tablespoon of mustard\n" +
                                   "10 slices of pepperoni\n" +
                                   "8 black grape\n" +
                                   "2-3 sliced carrot\n" +
                                   "2 tablespoon of humus")
                    .dinner("1/4 bowl of wholegrain pasta\n" +
                                    "1 tablespoon of olive oil\n" +
                                    "1 tablespoon of ricotta cheese\n" +
                                    "2 tablespoon of curd cheese\n" +
                                    "Half a bowl sliced spinach\n" +
                                    "Sliced half an onion \n" +
                                    "Half a bowl tomato puree\n" +
                                    "Mix all and heat until the cheese melts")
                    .build());

            //endregion

            //region Flat belly diet
            dietId = db.insert(Db.DietTable.TABLE_NAME, null, new Diet.Builder()
                    .name("Flatten Your Belly")
                    .categoryId(categoryId)
                    .info("Have a flat belly with this diet alongside your daily exercises.")
                    .build());

            db.insert(Db.DietDetailTable.TABLE_NAME, null, new DietDetail.Builder()
                    .name("Day 1/2/3/4/5/6/7")
                    .dietId(dietId)
                    .info("Drink a cup of water with lemon every morning 30 minute before breakfast.")
                    .breakfast("Omelet with cheese\n" +
                                       "1 teaspoon of honey\n" +
                                       "1 slice of wholegrain break\n" +
                                       "Green tea")
                    .lunch("Grilled chicken\n" +
                                   "Big portion of green salad\n" +
                                   "1 low-fat yogurt\n" +
                                   "Low calorie biscuits")
                    .dinner("Meatball\n" +
                                    "Lots of salad\n" +
                                    "1 yogurt")
                    .snack1("2 apricot")
                    .snack2("1 cup of coffee without milk and sugar")
                    .snack3("1 cup of green tea")
                    .snack4("1 slice of low-fat cheese\n" +
                                    "1 slice of wholemeal bread")
                    .build());

            //endregion

            //region Slimming Hips and Thighs in 7 Days
            dietId = db.insert(Db.DietTable.TABLE_NAME, null, new Diet.Builder()
                    .name("Slimming Hips and Thighs")
                    .categoryId(categoryId)
                    .info("Following this diet for at least 2-3 days a week make you lose your excess weight in 2 months.\n\n" +
                                  "Every morning drink 1 cup of warm water with lemon and a teaspoon of honey before breakfast.")
                    .build());

            db.insert(Db.DietDetailTable.TABLE_NAME, null, new DietDetail.Builder()
                    .name("Day 1/2/3/4/5/6/7")
                    .dietId(dietId)
                    .breakfast("1 peach\n" +
                                       "1 apple")
                    .lunch("Green salad\n" +
                                   "Grilled chicken")
                    .dinner("1 portion green bean\n" +
                                    "1 slice of wholemeal bread\n" +
                                    "Green salad")
                    .snack1("1 slice of low-fat cheese")
                    .snack3("5 oat biscuit")
                    .snack4("1 apple")
                    .snack5("1 cup of warm milk with cinnamon")
                    .build());

            //endregion

            categoryId = db.insert(Db.CategoryTable.TABLE_NAME, null, new Category.Builder()
                    .name("Custom Diets")
                    .info("Unpopular but effective collection of diets")
                    .order(4)
                    .build());

            //region Custom Diet 1
            dietId = db.insert(Db.DietTable.TABLE_NAME, null, new Diet.Builder()
                    .name("Custom Diet 1")
                    .categoryId(categoryId)
                    .info("Follow this diet for 7 days")
                    .build());

            db.insert(Db.DietDetailTable.TABLE_NAME, null, new DietDetail.Builder()
                    .name("Day 1/2/3/4/5/6/7")
                    .dietId(dietId)
                    .breakfast("2 slices of wheat bread\n" +
                                       "2 slices of cheese\n" +
                                       "Any amount of greens\n\n" +
                                       "For weekends add 2 boiled eggs and a cup of tea (sugar-free)")
                    .lunch("Soup\n" +
                                   "1 yogurt and salad\n" +
                                   "Any vegetable dish without potato and 1 toast")
                    .dinner("150-200 g of meat or fish\n" +
                                    "1 yogurt and salad")
                    .snack3("10-15 almonds/peanuts and handful of cereal with yogurt")
                    .snack5("An apple or orange")
                    .snack6("Green tea (sugar-free")
                    .build());

            //endregion

            //region Custom Diet 2
            dietId = db.insert(Db.DietTable.TABLE_NAME, null, new Diet.Builder()
                    .name("Custom Diet 2")
                    .categoryId(categoryId)
                    .info("Follow this diet for 7 days.")
                    .build());

            db.insert(Db.DietDetailTable.TABLE_NAME, null, new DietDetail.Builder()
                    .name("Day 1/2/3/4/5/6/7")
                    .dietId(dietId)
                    .breakfast("1 slice of cheese\n" +
                                       "1 boiled egg\n" +
                                       "4-5 olives\n" +
                                       "Any amount of greens and 2 toast")
                    .lunch("1 soup\n" +
                                   "Any fat-free vegetable dish\n" +
                                   "1 yogurt\n" +
                                   "1 toast")
                    .dinner("125 g meat or fish or any fat-free vegetable dish\n" +
                                    "1 yogurt\n" +
                                    "salad and 1 toast")
                    .snack1("An apple or orange")
                    .snack3("An apple or orange")
                    .snack4("1 low calorie biscuit")
                    .snack5("5-6 almonds")
                    .snack6("1 yogurt")
                    .build());

            //endregion

            //region Custom Diet 3
            dietId = db.insert(Db.DietTable.TABLE_NAME, null, new Diet.Builder()
                    .name("Custom Diet 3")
                    .categoryId(categoryId)
                    .info("Follow this diet for 7 days")
                    .build());

            db.insert(Db.DietDetailTable.TABLE_NAME, null, new DietDetail.Builder()
                    .name("Day 1/2/3/4/5/6/7")
                    .dietId(dietId)
                    .breakfast("2 slices of cheese\n" +
                                       "1 tomato\n" +
                                       "1 toast ")
                    .lunch("Any fat-free vegetable dish\n" +
                                   "1 yogurt\n" +
                                   "1 toast")
                    .dinner("150 g of meat or fish\n" +
                                    "Salad")
                    .snack1("10 almonds")
                    .snack3("3 dried fruit")
                    .snack4("2 walnut")
                    .snack5("An apple or orange")
                    .build());

            //endregion

            //region Custom Diet 4
            dietId = db.insert(Db.DietTable.TABLE_NAME, null, new Diet.Builder()
                    .name("Custom Diet 4")
                    .categoryId(categoryId)
                    .info("Follow this diet for 7 days.")
                    .build());

            db.insert(Db.DietDetailTable.TABLE_NAME, null, new DietDetail.Builder()
                    .name("Day 1/2/3/4/5/6/7")
                    .dietId(dietId)
                    .breakfast("1 slice of cheese\n" +
                                       "1 boiled egg\n" +
                                       "Any amount of greens\n" +
                                       "1 toast")
                    .lunch("Soup\n" +
                                   "150 g of meat or fish\n" +
                                   "1 yogurt and 1 toast")
                    .dinner("Fruit salad")
                    .snack1("2 walnut")
                    .snack2("1 yogurt")
                    .snack3("1 low calorie biscuit\n" +
                                    "1 slice of cheese")
                    .snack5("1 yogurt")
                    .build());

            //endregion

            //region Vegetarian Diet
            dietId = db.insert(Db.DietTable.TABLE_NAME, null, new Diet.Builder()
                    .name("Vegetarian Diet")
                    .categoryId(categoryId)
                    .info("Follow this diet for 7 days.")
                    .build());

            db.insert(Db.DietDetailTable.TABLE_NAME, null, new DietDetail.Builder()
                    .name("Day 1/2/3/4/5/6/7")
                    .dietId(dietId)
                    .breakfast("A handful of cereal with yogurt")
                    .lunch("Soup\n" +
                                   "Any fat-free vegetable dish\n" +
                                   "1 yogurt\n" +
                                   "1 toast")
                    .dinner("Cheese salad")
                    .snack1("1 any fruit")
                    .snack3("1 slice of cheese\n" +
                                    "1 toast")
                    .snack5("1 yogurt with half a lemon")
                    .build());

            //endregion

            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Db.CategoryTable.onUpdate(db);
        Db.DietTable.onUpdate(db);
        Db.DietDetailTable.onUpdate(db);
    }
}

