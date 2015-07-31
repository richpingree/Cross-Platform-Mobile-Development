//
//  AddViewController.h
//  ParseStarterProject
//
//  Created by Richard Pingree on 7/31/15.
//
//

#import <UIKit/UIKit.h>
#import <Parse/Parse.h>

@interface AddViewController : UIViewController
@property (weak, nonatomic) IBOutlet UITextField *heroName;
@property (weak, nonatomic) IBOutlet UITextField *heroId;
- (IBAction)SaveButton:(id)sender;

@end
