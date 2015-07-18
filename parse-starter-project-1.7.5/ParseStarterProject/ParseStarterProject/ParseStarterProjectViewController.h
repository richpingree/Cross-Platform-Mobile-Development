//
//  ParseStarterProjectViewController.h
//
//  Copyright 2011-present Parse Inc. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "Parse/Parse.h"

@interface ParseStarterProjectViewController : UIViewController <UITableViewDelegate, UITableViewDataSource>
{
    NSMutableArray *objectArray;
}

@property (weak, nonatomic) IBOutlet UITableView *myTableView;
- (IBAction)logoutButton:(id)sender;
- (IBAction)editButton:(id)sender;
- (IBAction)refreshButton:(id)sender;

@end
