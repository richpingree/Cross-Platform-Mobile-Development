//
//  AddViewController.m
//  ParseStarterProject
//
//  Created by Richard Pingree on 7/31/15.
//
//

#import "AddViewController.h"

@interface AddViewController ()

@end

@implementation AddViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    // Do any additional setup after loading the view.
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

- (IBAction)SaveButton:(id)sender {
    PFObject *hero = [PFObject objectWithClassName:@"Hero"];
    hero[@"Name"] = _heroName.text;
    hero[@"Id"] = _heroId.text;
    hero[@"User"] = [NSString stringWithFormat:@"%@", [[PFUser currentUser] valueForKey:@"username"]];
                     
                     [hero saveEventually:^(BOOL succeeded, NSError *error){
        if (succeeded){
            _heroName.text = nil;
            _heroId.text = nil;
            //The object has been saved.
            UIAlertView *alert = [[UIAlertView alloc] initWithTitle:@"Entry was Saved" message:@"Add Another" delegate:nil cancelButtonTitle:@"Ok" otherButtonTitles: nil];
            [alert show];
        } else{
            //There was a problem, check error.description
            UIAlertView *alert = [[UIAlertView alloc] initWithTitle:@"Ooops!" message:@"Unable to Save" delegate:nil cancelButtonTitle:@"Ok" otherButtonTitles: nil];
            [alert show];
            
        }
    }];
}
@end
