//
//  LoginViewController.h
//  ParseStarterProject
//
//  Created by Richard Pingree on 7/16/15.
//
//

#import <UIKit/UIKit.h>
#import "Parse/Parse.h"

@interface LoginViewController : UIViewController

@property (weak, nonatomic) IBOutlet UITextField *usernameField;
@property (weak, nonatomic) IBOutlet UITextField *passwordField;
@property (weak, nonatomic) IBOutlet UITextField *reEnterPasswordField;
@property (weak, nonatomic) IBOutlet UIView *loginOverlayView;

- (IBAction)signUpAction:(id)sender;
- (IBAction)signedUpButton:(id)sender;

- (IBAction)loginButton:(id)sender;
@property (weak, nonatomic) IBOutlet UITextField *loginUsername;
@property (weak, nonatomic) IBOutlet UITextField *loginPassword;

@end
