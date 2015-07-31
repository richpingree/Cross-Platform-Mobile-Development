//
//  DetailViewController.h
//  ParseStarterProject
//
//  Created by Richard Pingree on 7/30/15.
//
//

#import <UIKit/UIKit.h>
#import <Parse/Parse.h>

@interface DetailViewController : UIViewController
@property (weak, nonatomic) NSString *nameString;
@property (weak, nonatomic) NSString *idString;
@property (weak, nonatomic) NSString *objectIdString;
@property (weak, nonatomic) IBOutlet UITextField *detailHeroName;
@property (weak, nonatomic) IBOutlet UITextField *detailHeroId;
- (IBAction)Save:(id)sender;


@end
