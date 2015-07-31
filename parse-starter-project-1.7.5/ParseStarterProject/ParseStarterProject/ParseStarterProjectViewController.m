//
//  ParseStarterProjectViewController.m
//
//  Copyright 2011-present Parse Inc. All rights reserved.
//

#import "ParseStarterProjectViewController.h"
#import "Reachability.h"
#import "DetailViewController.h"

#import <Parse/Parse.h>

@implementation ParseStarterProjectViewController

@synthesize myTableView;
#pragma mark -
#pragma mark UIViewController

// Implement viewDidLoad to do additional setup after loading the view, typically from a nib.
- (void)viewDidLoad {
    
    [super viewDidLoad];
    
    NSLog(@"Array length: %lu", (unsigned long)objectArray.count);
    Reachability *networkReachability = [Reachability reachabilityForInternetConnection];
    NetworkStatus networkStatus = [networkReachability currentReachabilityStatus];
    if (networkStatus == NotReachable) {
        NSLog(@"No Network Found");
        UIAlertView *alert = [[UIAlertView alloc] initWithTitle:@"No Connection Found!" message:@"please try again later" delegate:self cancelButtonTitle:@"Ok" otherButtonTitles:nil];
        [alert show];
    }else{
        NSLog(@"Connected to Network");
        [self performSelector:@selector(retrieveFromParse)];
    }
}

- (void) retrieveFromParse{
    
    PFQuery *retrieveObjects = [PFQuery queryWithClassName:@"Hero"];
    
    [retrieveObjects findObjectsInBackgroundWithBlock:^(NSArray *objects, NSError *error){
        
        if (!error) {
            objectArray = [[NSMutableArray alloc] initWithArray:objects];
        }
        [myTableView reloadData];
    }];
}

- (void)didReceiveMemoryWarning {
    // Releases the view if it doesn't have a superview.
    [super didReceiveMemoryWarning];

    // Release any cached data, images, etc that aren't in use.
}

- (BOOL)shouldAutorotateToInterfaceOrientation:(UIInterfaceOrientation)interfaceOrientation {
    // Return YES for supported orientations
    return (interfaceOrientation == UIInterfaceOrientationPortrait);
}

- (IBAction)logoutButton:(id)sender {
    [PFUser logOut];
    [self dismissViewControllerAnimated:YES completion:nil];
}

- (IBAction)editButton:(id)sender {
    myTableView.editing = !myTableView.isEditing;
}

- (IBAction)refreshButton:(id)sender {
    [self performSelector:@selector(retrieveFromParse)];
}

- (void)tableView:(UITableView *)tableView commitEditingStyle:(UITableViewCellEditingStyle)editingStyle forRowAtIndexPath:(NSIndexPath *)indexPath
{
    if (editingStyle == UITableViewCellEditingStyleDelete) {
        [objectArray removeObjectAtIndex:indexPath.row];
        
        PFObject *tempObject = [objectArray objectAtIndex:indexPath.row];
//
//        [tempObject deleteInBackgroundWithBlock:^(BOOL successed, NSError *error){
//            
//        }];
        
        
        [tableView deleteRowsAtIndexPaths:[NSMutableArray arrayWithObject:indexPath] withRowAnimation:UITableViewRowAnimationAutomatic];
        
        PFQuery *query = [PFQuery queryWithClassName:@"Hero"];
        PFObject *object = [query getObjectWithId:tempObject.objectId];
        [object delete];
        }
}

- (NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section
{
    return objectArray.count;
}

- (UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath
{
    UITableViewCell *cell = [tableView dequeueReusableCellWithIdentifier:@"myCell"];
    if (cell != nil) {
        
        PFObject *tempObject = [objectArray objectAtIndex:indexPath.row];
        
        cell.textLabel.text = [tempObject objectForKey:@"Name"];
        cell.detailTextLabel.text = [tempObject objectForKey:@"Id"];
    }
    return cell;
}

-(void) prepareForSegue:(UIStoryboardSegue *)segue sender:(id)sender{
    
//    DetailViewController *detailViewController = segue.destinationViewController;
//    if (detailViewController != nil) {
//        UITableViewCell *cell = (UITableViewCell*)sender;
//        NSIndexPath *indexPath =[myTableView indexPathForCell:cell];
//        
//        PFObject *tempObject = [objectArray objectAtIndex:indexPath.row];
//        
//        NSString *nameString = [tempObject objectForKey:@"Name"];
//        NSString *idString = [tempObject objectForKey:@"Id"];
//        NSString *objectIdString = [tempObject objectId];
//        
//       // NSLog(@"Test: %@", nameString);
//        detailViewController.nameString = nameString;
//        detailViewController.idString =idString;
//        detailViewController.objectIdString = objectIdString;
//
//
//    }
    
}
@end
