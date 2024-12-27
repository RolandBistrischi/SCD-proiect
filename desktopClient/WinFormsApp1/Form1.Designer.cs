namespace WinFormsApp1
{
    partial class Form1
    {
        /// <summary>
        ///  Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        ///  Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        ///  Required method for Designer support - do not modify
        ///  the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            comboBox1 = new ComboBox();
            label1 = new Label();
            button1 = new Button();
            tabControl1 = new TabControl();
            tabPageBusyCourier = new TabPage();
            tabPageAllCourierPending = new TabPage();
            buttonLoadAllCourier = new Button();
            comboBoxPending = new ComboBox();
            label2 = new Label();
            tabPageAllManagers = new TabPage();
            dataGridViewManagersAndNumbers = new DataGridView();
            buttonLoadManagers = new Button();
            label3 = new Label();
            tabPageSendEmail = new TabPage();
            richTextBoxBody = new RichTextBox();
            checkedListBox1 = new CheckedListBox();
            textBoxSubject = new TextBox();
            buttonSendEmail = new Button();
            buttonLoadCouriers = new Button();
            label4 = new Label();
            tabControl1.SuspendLayout();
            tabPageBusyCourier.SuspendLayout();
            tabPageAllCourierPending.SuspendLayout();
            tabPageAllManagers.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)dataGridViewManagersAndNumbers).BeginInit();
            tabPageSendEmail.SuspendLayout();
            SuspendLayout();
            // 
            // comboBox1
            // 
            comboBox1.FormattingEnabled = true;
            comboBox1.Location = new Point(143, 33);
            comboBox1.Name = "comboBox1";
            comboBox1.Size = new Size(151, 28);
            comboBox1.TabIndex = 0;
            // 
            // label1
            // 
            label1.AutoSize = true;
            label1.Location = new Point(29, 36);
            label1.Name = "label1";
            label1.Size = new Size(95, 20);
            label1.TabIndex = 1;
            label1.Text = "busy couriers";
            // 
            // button1
            // 
            button1.Location = new Point(384, 35);
            button1.Name = "button1";
            button1.Size = new Size(184, 29);
            button1.TabIndex = 2;
            button1.Text = "load";
            button1.UseVisualStyleBackColor = true;
            button1.Click += button1_Click;
            // 
            // tabControl1
            // 
            tabControl1.Controls.Add(tabPageBusyCourier);
            tabControl1.Controls.Add(tabPageAllCourierPending);
            tabControl1.Controls.Add(tabPageAllManagers);
            tabControl1.Controls.Add(tabPageSendEmail);
            tabControl1.Location = new Point(12, 23);
            tabControl1.Name = "tabControl1";
            tabControl1.SelectedIndex = 0;
            tabControl1.Size = new Size(765, 392);
            tabControl1.TabIndex = 3;
            // 
            // tabPageBusyCourier
            // 
            tabPageBusyCourier.Controls.Add(button1);
            tabPageBusyCourier.Controls.Add(comboBox1);
            tabPageBusyCourier.Controls.Add(label1);
            tabPageBusyCourier.Location = new Point(4, 29);
            tabPageBusyCourier.Name = "tabPageBusyCourier";
            tabPageBusyCourier.Padding = new Padding(3);
            tabPageBusyCourier.Size = new Size(757, 359);
            tabPageBusyCourier.TabIndex = 0;
            tabPageBusyCourier.Text = "busy courier";
            tabPageBusyCourier.UseVisualStyleBackColor = true;
            // 
            // tabPageAllCourierPending
            // 
            tabPageAllCourierPending.Controls.Add(buttonLoadAllCourier);
            tabPageAllCourierPending.Controls.Add(comboBoxPending);
            tabPageAllCourierPending.Controls.Add(label2);
            tabPageAllCourierPending.Location = new Point(4, 29);
            tabPageAllCourierPending.Name = "tabPageAllCourierPending";
            tabPageAllCourierPending.Padding = new Padding(3);
            tabPageAllCourierPending.Size = new Size(757, 359);
            tabPageAllCourierPending.TabIndex = 1;
            tabPageAllCourierPending.Text = "all courier";
            tabPageAllCourierPending.UseVisualStyleBackColor = true;
            // 
            // buttonLoadAllCourier
            // 
            buttonLoadAllCourier.Location = new Point(423, 155);
            buttonLoadAllCourier.Name = "buttonLoadAllCourier";
            buttonLoadAllCourier.Size = new Size(184, 29);
            buttonLoadAllCourier.TabIndex = 5;
            buttonLoadAllCourier.Text = "load";
            buttonLoadAllCourier.UseVisualStyleBackColor = true;
            buttonLoadAllCourier.Click += buttonLoadAllCourier_Click;
            // 
            // comboBoxPending
            // 
            comboBoxPending.FormattingEnabled = true;
            comboBoxPending.Location = new Point(44, 155);
            comboBoxPending.Name = "comboBoxPending";
            comboBoxPending.Size = new Size(250, 28);
            comboBoxPending.TabIndex = 3;
            // 
            // label2
            // 
            label2.AutoSize = true;
            label2.Location = new Point(44, 47);
            label2.Name = "label2";
            label2.Size = new Size(242, 20);
            label2.TabIndex = 4;
            label2.Text = "Couriers without pending packages";
            // 
            // tabPageAllManagers
            // 
            tabPageAllManagers.Controls.Add(dataGridViewManagersAndNumbers);
            tabPageAllManagers.Controls.Add(buttonLoadManagers);
            tabPageAllManagers.Controls.Add(label3);
            tabPageAllManagers.Location = new Point(4, 29);
            tabPageAllManagers.Name = "tabPageAllManagers";
            tabPageAllManagers.Size = new Size(757, 359);
            tabPageAllManagers.TabIndex = 2;
            tabPageAllManagers.Text = "All managers";
            tabPageAllManagers.UseVisualStyleBackColor = true;
            // 
            // dataGridViewManagersAndNumbers
            // 
            dataGridViewManagersAndNumbers.ColumnHeadersHeightSizeMode = DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            dataGridViewManagersAndNumbers.Location = new Point(20, 89);
            dataGridViewManagersAndNumbers.Name = "dataGridViewManagersAndNumbers";
            dataGridViewManagersAndNumbers.RowHeadersWidth = 51;
            dataGridViewManagersAndNumbers.RowTemplate.Height = 29;
            dataGridViewManagersAndNumbers.Size = new Size(661, 247);
            dataGridViewManagersAndNumbers.TabIndex = 9;
            // 
            // buttonLoadManagers
            // 
            buttonLoadManagers.Location = new Point(360, 27);
            buttonLoadManagers.Name = "buttonLoadManagers";
            buttonLoadManagers.Size = new Size(184, 29);
            buttonLoadManagers.TabIndex = 5;
            buttonLoadManagers.Text = "load";
            buttonLoadManagers.UseVisualStyleBackColor = true;
            buttonLoadManagers.Click += button3_Click;
            // 
            // label3
            // 
            label3.AutoSize = true;
            label3.Location = new Point(32, 47);
            label3.Name = "label3";
            label3.Size = new Size(224, 20);
            label3.TabIndex = 4;
            label3.Text = "Managers and delivered number";
            // 
            // tabPageSendEmail
            // 
            tabPageSendEmail.Controls.Add(richTextBoxBody);
            tabPageSendEmail.Controls.Add(checkedListBox1);
            tabPageSendEmail.Controls.Add(textBoxSubject);
            tabPageSendEmail.Controls.Add(buttonSendEmail);
            tabPageSendEmail.Controls.Add(buttonLoadCouriers);
            tabPageSendEmail.Controls.Add(label4);
            tabPageSendEmail.Location = new Point(4, 29);
            tabPageSendEmail.Name = "tabPageSendEmail";
            tabPageSendEmail.Size = new Size(757, 359);
            tabPageSendEmail.TabIndex = 3;
            tabPageSendEmail.Text = "Send Email";
            tabPageSendEmail.UseVisualStyleBackColor = true;
            // 
            // richTextBoxBody
            // 
            richTextBoxBody.Location = new Point(476, 46);
            richTextBoxBody.Name = "richTextBoxBody";
            richTextBoxBody.Size = new Size(264, 203);
            richTextBoxBody.TabIndex = 10;
            richTextBoxBody.Text = "";
            // 
            // checkedListBox1
            // 
            checkedListBox1.FormattingEnabled = true;
            checkedListBox1.Location = new Point(14, 83);
            checkedListBox1.Name = "checkedListBox1";
            checkedListBox1.Size = new Size(182, 202);
            checkedListBox1.TabIndex = 9;
            // 
            // textBoxSubject
            // 
            textBoxSubject.Location = new Point(227, 278);
            textBoxSubject.Name = "textBoxSubject";
            textBoxSubject.Size = new Size(209, 27);
            textBoxSubject.TabIndex = 8;
            // 
            // buttonSendEmail
            // 
            buttonSendEmail.Location = new Point(242, 168);
            buttonSendEmail.Name = "buttonSendEmail";
            buttonSendEmail.Size = new Size(184, 29);
            buttonSendEmail.TabIndex = 6;
            buttonSendEmail.Text = "Send";
            buttonSendEmail.UseVisualStyleBackColor = true;
            buttonSendEmail.Click += buttonSendEmail_Click;
            // 
            // buttonLoadCouriers
            // 
            buttonLoadCouriers.Location = new Point(242, 83);
            buttonLoadCouriers.Name = "buttonLoadCouriers";
            buttonLoadCouriers.Size = new Size(184, 29);
            buttonLoadCouriers.TabIndex = 5;
            buttonLoadCouriers.Text = "load";
            buttonLoadCouriers.UseVisualStyleBackColor = true;
            buttonLoadCouriers.Click += buttonLoadCouriers_Click;
            // 
            // label4
            // 
            label4.AutoSize = true;
            label4.Location = new Point(14, 34);
            label4.Name = "label4";
            label4.Size = new Size(163, 20);
            label4.TabIndex = 4;
            label4.Text = "Send emails to couriers";
            // 
            // Form1
            // 
            AutoScaleDimensions = new SizeF(8F, 20F);
            AutoScaleMode = AutoScaleMode.Font;
            ClientSize = new Size(800, 450);
            Controls.Add(tabControl1);
            Name = "Form1";
            Text = "Package tracking";
            tabControl1.ResumeLayout(false);
            tabPageBusyCourier.ResumeLayout(false);
            tabPageBusyCourier.PerformLayout();
            tabPageAllCourierPending.ResumeLayout(false);
            tabPageAllCourierPending.PerformLayout();
            tabPageAllManagers.ResumeLayout(false);
            tabPageAllManagers.PerformLayout();
            ((System.ComponentModel.ISupportInitialize)dataGridViewManagersAndNumbers).EndInit();
            tabPageSendEmail.ResumeLayout(false);
            tabPageSendEmail.PerformLayout();
            ResumeLayout(false);
        }

        #endregion

        private ComboBox comboBox1;
        private Label label1;
        private Button button1;
        private TabControl tabControl1;
        private TabPage tabPageBusyCourier;
        private TabPage tabPageAllCourierPending;
        private TabPage tabPageAllManagers;
        private TabPage tabPageSendEmail;
        private Button buttonLoadAllCourier;
        private ComboBox comboBoxPending;
        private Label label2;
        private Button buttonLoadManagers;
        private Label label3;
        private Button buttonLoadCouriers;
        private Label label4;
        private CheckedListBox checkedListBox1;
        private TextBox textBoxSubject;
        private Button buttonSendEmail;
        private RichTextBox richTextBoxBody;
        private DataGridView dataGridViewManagersAndNumbers;
    }
}