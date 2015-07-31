//
//  DetailViewController.m
//  ParseStarterProject
//
//  Created by Richard Pingree on 7/30/15.
//
//

#import "DetailViewController.h"

@interface DetailViewController ()


@end

@implementation DetailViewController
@synthesize nameString, idString, objectIdString, detailHeroName, detailHeroId;

- (void)viewDidLoad {
    [super viewDidLoad];
    // Do any additional setup after loading the view.
    
    detailHeroName.text = self.nameString;
    detailHeroId.text = self.idString;
    
    
}

- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

/*
#pragma mark - Navigation

// In a storyboard-based application, you will often want to do a little preparation before navigation
- (void)prepareForSegue:(UIStoryboardSegue *)segue sender:(id)sender {
    // Get the new view controller using [segue destinationViewController].
    // Pass the selected object to the new view controller.
}
*/

- (IBAction)Save:(id)sender {
    
    NSString *currentObject = self.objectIdString;
    
    PFQuery *query = [PFQuery queryWithClassName:@"Hero"];
    
    // Retrieve the object by id
    [query getObjectInBackgroundWithId:currentObject block:^(PFObject *hero, NSError *error) {
            // Now let's update it with some new data. In this case, only cheatMode and score
            // will get sent to the cloud. playerName hasn't changed.
            hero[@"Name"] = detailHeroName.text;
            hero[@"Id"] = detailHeroId.text;
            [hero saveEventually:^(BOOL succeeded, NSError *error){
                if (succeeded) {
                    // The object has been saved.
                    UIAlertView *alert = [[UIAlertView alloc] initWithTitle:@"Entry was Saved" message:@"Update Successful" delegate:nil cancelButtonTitle:@"Ok" otherButtonTitles:nil ];
                    [alert show];
                }else {
                // There was a problem, check error.description
                    UIAlertView *alert = [[UIAlertView alloc] initWithTitle:@"Ooops!" message:@"Unable to Save." delegate:nil cancelButtonTitle:@"Ok" otherButtonTitles:nil ];
                    [alert show];
                }

            }];
    }];
}
@end
