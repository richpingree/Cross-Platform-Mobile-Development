//
//  LoginViewController.m
//  ParseStarterProject
//
//  Created by Richard Pingree on 7/16/15.
//
//

#import "LoginViewController.h"

@interface LoginViewController ()

@end

@implementation LoginViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    // Do any additional setup after loading the view.
    
    }

- (void)viewDidAppear:(BOOL)animated{
    PFUser *user = [PFUser currentUser];
    if (user.username != nil) {
        [self performSegueWithIdentifier:@"login" sender:self];
    }

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

- (IBAction)signUpAction:(id)sender {
    [_usernameField resignFirstResponder];
    [_passwordField resignFirstResponder];
    [_reEnterPasswordField resignFirstResponder];
    _loginUsernameField.text = nil;
    _loginPasswordField.text = nil;
    _usernameField.text = nil;
    _passwordField.text = nil;
    _reEnterPasswordField.text = nil;
    [self checkFieldComplete];
    
}


- (void) checkFieldComplete{
    if ([_usernameField.text isEqualToString:@""] || [_passwordField.text isEqualToString:@""] || [_reEnterPasswordField.text isEqualToString:@""] ) {
        UIAlertView *alert = [[UIAlertView alloc] initWithTitle:@"Ooops!" message:@"All Fields are Required." delegate:nil cancelButtonTitle:@"Ok" otherButtonTitles:nil ];
        [alert show];
    }
    else{
        [self checkPasswordMatch];
    }
}

- (void) checkPasswordMatch {
    if (![_passwordField.text isEqualToString:_reEnterPasswordField.text]) {
        UIAlertView *alert = [[UIAlertView alloc] initWithTitle:@"Ooops!" message:@"Passwords don't match." delegate:nil cancelButtonTitle:@"Ok" otherButtonTitles:nil ];
        [alert show];

    }
    else{
        
        [self signUpNewUser];
        
    }
}

- (void) signUpNewUser {
    
    PFUser *newUser = [PFUser user];
    newUser.username = _usernameField.text;
    newUser.password = _passwordField.text;
    
    [newUser signUpInBackgroundWithBlock:^(BOOL succeeded, NSError *error){
        if (!error) {
            NSLog(@"Sign Up success!");
            
            [self performSegueWithIdentifier:@"login" sender:self];
        }
        else{
            NSLog(@"Error on Sign Up.");
            UIAlertView *alert = [[UIAlertView alloc] initWithTitle:@"Ooops!" message:@"Username is already taken" delegate:nil cancelButtonTitle:@"Ok" otherButtonTitles:nil ];
            [alert show];
        }
    }];
}



- (IBAction)loginButton:(id)sender {
    [PFUser logInWithUsernameInBackground:_loginUsernameField.text password:_loginPasswordField.text block:^(PFUser *user, NSError *error){
        if (!error) {
            NSLog(@"Login Successful!");
            _loginUsernameField.text = nil;
            _loginPasswordField.text = nil;
            _usernameField.text = nil;
            _passwordField.text = nil;
            _reEnterPasswordField.text = nil;
            [self performSegueWithIdentifier:@"login" sender:self];
        }
        if (error) {
            UIAlertView *alert = [[UIAlertView alloc] initWithTitle:@"Ooops!" message:@"Username blank or already taken!" delegate:nil cancelButtonTitle:@"Ok" otherButtonTitles:nil ];
            [alert show];
            
        }
    }];
}


@end
