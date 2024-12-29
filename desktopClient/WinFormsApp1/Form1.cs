using System.Reactive.Subjects;
using static System.Windows.Forms.VisualStyles.VisualStyleElement.Tab;

namespace WinFormsApp1
{
    public partial class Form1 : Form
    {
        private readonly PackageService packageService;
        private readonly CourierService courseService;
        // private readonly List<Package> packageList = new();
        public Form1()
        {
            InitializeComponent();
            packageService = new PackageService();
            packageService.createConnection();

            courseService = new CourierService();
        }

        private void button1_Click(object sender, EventArgs e)
        {
            var couriersList = packageService.GetBusyCouriers();

            comboBox1.DataSource = couriersList;
            comboBox1.DisplayMember = "name";
        }

        private void buttonLoadAllCourier_Click(object sender, EventArgs e)
        {
            var courierList = courseService.GetAllCouriersWithoutPendingPackages();
            comboBoxPending.DataSource = courierList;
            comboBoxPending.DisplayMember = "name";
        }

        private void button3_Click(object sender, EventArgs e)
        {
            var managers = courseService.GetAllManagersAndTheirNumberOfPackages();

            dataGridViewManagersAndNumbers.Rows.Clear();

            if (dataGridViewManagersAndNumbers.Columns.Count == 0)
            {
                dataGridViewManagersAndNumbers.Columns.Add("CourierName", "Courier Name");
                dataGridViewManagersAndNumbers.Columns.Add("DeliveredCount", "Delivered Count");
            }

            var rows = new List<DataGridViewRow>();
            foreach (var manager in managers)
            {
                var row = new DataGridViewRow();

                row.Cells.Add(new DataGridViewTextBoxCell() { Value = manager.courier?.Name ?? "Unknown" });
                row.Cells.Add(new DataGridViewTextBoxCell() { Value = manager.deliveredCount });

                rows.Add(row);
            }

            dataGridViewManagersAndNumbers.Rows.AddRange(rows.ToArray());
            dataGridViewManagersAndNumbers.Columns["CourierName"].AutoSizeMode = DataGridViewAutoSizeColumnMode.Fill;
            dataGridViewManagersAndNumbers.Columns["DeliveredCount"].AutoSizeMode = DataGridViewAutoSizeColumnMode.Fill;
        }


        private void buttonLoadCouriers_Click(object sender, EventArgs e)
        {
            var couriers = courseService.GetCouriers();
            checkedListBox1.DataSource = couriers;
            checkedListBox1.DisplayMember = "name";
        }

        private void buttonSendEmail_Click(object sender, EventArgs e)
        {
            var subject = textBoxSubject.Text.Trim();
            var body = richTextBoxBody.Text.Trim();

            if (string.IsNullOrWhiteSpace(subject) || string.IsNullOrWhiteSpace(body))
            {
                MessageBox.Show("Subiectul și corpul email-ului sunt obligatorii!");
                return;
            }

            var selectedCouriers = checkedListBox1.CheckedItems.Cast<Courier>().ToList();
            if (!selectedCouriers.Any())
            {
                MessageBox.Show("Nu ați selectat niciun curier!");
                return;
            }
            var emailRequest = new EmailRequest
            {
                Emails = selectedCouriers.Select(c => c.Email).ToList(),
                Subject = subject,
                Body = body
            };

            try
            {
                courseService.SendEmailToSelectedCouriers(emailRequest);
            }
            catch (Exception ex)
            {
                MessageBox.Show($"Eroare la trimiterea email-urilor: {ex.Message}");
                return;
            }

        }
    }
}